package ru.tinkoff.landscape.repository;

import org.springframework.data.jpa.repository.Query;
import ru.tinkoff.landscape.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.landscape.enums.Skill;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Get distinct skills by merging all skills from all orders using jpql query
     *
     * @return {@code Set<Skill>}
     */
    @Query(value = "select distinct skills from order_skills", nativeQuery = true)
    Set<Skill> findDistinctSkills();

    /**
     * Get orders with null user ordered by length of skills using jpql query
     *
     * @return {@code List<Order>}
     */
    @Query("select o from Order o where o.user is null order by size(o.skills)")
    List<Order> findOrdersWithNullUserOrderBySkillSize();
}
