package com.tinkoffacademy.landscape.config;

import com.tinkoffacademy.landscape.dto.GardenerDto;
import com.tinkoffacademy.landscape.dto.OrderDto;
import com.tinkoffacademy.landscape.dto.UserDto;
import com.tinkoffacademy.landscape.entity.Field;
import com.tinkoffacademy.landscape.entity.Gardener;
import com.tinkoffacademy.landscape.entity.Order;
import com.tinkoffacademy.landscape.entity.User;
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
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        // configure ModelMapper to skip null fields
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        modelMapper.typeMap(UserDto.class, User.class)
                .setPostConverter(context -> {
                    User user = context.getDestination();
                    if (user.getId() == null) {
                        user.getUserAccounts().forEach(userAccount -> userAccount.setId(null));
                    }
                    return user;
                });
        // set post converter for GardenerDto to Gardener mapping
        modelMapper.typeMap(GardenerDto.class, Gardener.class)
                .setPostConverter(context -> {
                    Gardener gardener = context.getDestination();
                    if (gardener.getId() == null) {
                        gardener.getFields().forEach(field -> field.setId(null));
                    }
                    return gardener;
                });
        configureOrderToOrderDtoMapping(modelMapper);
        return modelMapper;
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
