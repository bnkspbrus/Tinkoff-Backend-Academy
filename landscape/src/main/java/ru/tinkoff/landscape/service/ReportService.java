package ru.tinkoff.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.landscape.dto.Report;
import ru.tinkoff.landscape.entity.User;
import ru.tinkoff.landscape.entity.Order;
import ru.tinkoff.landscape.repository.OrderRepository;
import ru.tinkoff.landscape.repository.UserRepository;
import ru.tinkoff.landscape.utils.KuhnAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Report getOrdersReport() {
        return new Report(
                orderRepository.findDistinctSkills(),
                userRepository.findAllUserToSkills(),
                isDeficit()
        );
    }

    private boolean isDeficit() {
        List<Order> ordersWithNullUser = orderRepository.findOrdersWithNullUserOrderBySkillSize();
        List<User> usersWithNullOrders = userRepository.findAllByOrdersIsNullOrderBySkillSize();
        if (usersWithNullOrders.size() > ordersWithNullUser.size()) {
            return true;
        }
        Map<Long, List<Long>> userIdToOrderIds = new HashMap<>();
        for (User user : usersWithNullOrders) {
            for (Order order : ordersWithNullUser) {
                if (order.getSkills().size() > user.getSkills().size()) {
                    continue;
                }
                if (user.getSkills().containsAll(order.getSkills())) {
                    if (userIdToOrderIds.containsKey(user.getId())) {
                        userIdToOrderIds.get(user.getId()).add(order.getId());
                    } else {
                        List<Long> orderIds = new ArrayList<>();
                        orderIds.add(order.getId());
                        userIdToOrderIds.put(user.getId(), orderIds);
                    }
                }
            }
        }
        KuhnAlgorithm kuhnAlgorithm = new KuhnAlgorithm(userIdToOrderIds);
        return kuhnAlgorithm.getMatching().size() < usersWithNullOrders.size();
    }
}
