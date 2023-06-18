package com.tinkoffacademy.landscape.config;

import com.tinkoffacademy.landscape.dto.GardenerDto;
import com.tinkoffacademy.landscape.dto.OrderDto;
import com.tinkoffacademy.landscape.dto.UserDto;
import com.tinkoffacademy.landscape.entity.Field;
import com.tinkoffacademy.landscape.entity.Gardener;
import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.entity.User;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final AccountRepository accountRepository;
    private final FieldRepository fieldRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        // configure ModelMapper to skip null fields
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        configureUserDtoToUserPostConverter(modelMapper);
        // set post converter for GardenerDto to Gardener mapping
        configureGardenerDtoToGardenerPostConverter(modelMapper);
        configureOrderToOrderDtoMapping(modelMapper);
        configureOrderDtoToOrderMapping(modelMapper);
        return modelMapper;
    }

    private void configureOrderDtoToOrderMapping(ModelMapper modelMapper) {
        Converter<Long, Field> longToFieldConverter = context -> fieldRepository.findById(context.getSource()).orElse(null);
        modelMapper.typeMap(OrderDto.class, Order.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(longToFieldConverter)
                        .map(OrderDto::getGardenId, Order::setGarden)
                );
        Converter<Long, User> longToUserConverter = context -> (User) accountRepository.findById(context.getSource()).orElse(null);
        modelMapper.typeMap(OrderDto.class, Order.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(longToUserConverter)
                        .map(OrderDto::getUserId, Order::setUser)
                );
    }

    private void configureUserDtoToUserPostConverter(ModelMapper modelMapper) {
        modelMapper.typeMap(UserDto.class, User.class)
                .setPostConverter(context -> {
                    User user = context.getDestination();
                    if (user.getId() == null) {
                        user.getUserAccounts().forEach(userAccount -> userAccount.setId(null));
                    }
                    return user;
                });
    }

    private void configureGardenerDtoToGardenerPostConverter(ModelMapper modelMapper) {
        modelMapper.typeMap(GardenerDto.class, Gardener.class)
                .setPostConverter(context -> {
                    Gardener gardener = context.getDestination();
                    if (gardener.getId() == null) {
                        gardener.getFields().forEach(field -> field.setId(null));
                    }
                    return gardener;
                });
    }

    private void configureOrderToOrderDtoMapping(ModelMapper modelMapper) {
        // set gardenId of OrderDto to id of Garden of Order using Converter
        Converter<Field, Long> orderToGardenIdConverter = context -> context.getSource().getId();
        modelMapper.typeMap(Order.class, OrderDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(orderToGardenIdConverter)
                        .map(Order::getGarden, OrderDto::setGardenId));
        // set userId of OrderDto to id of User of Order using Converter
        Converter<User, Long> orderToUserIdConverter = context -> context.getSource().getId();
        modelMapper.typeMap(Order.class, OrderDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(orderToUserIdConverter)
                        .map(Order::getUser, OrderDto::setUserId));
    }
}
