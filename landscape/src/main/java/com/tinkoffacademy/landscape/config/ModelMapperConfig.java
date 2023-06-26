package com.tinkoffacademy.landscape.config;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.dto.GardenerDto;
import com.tinkoffacademy.landscape.dto.OrderDto;
import com.tinkoffacademy.landscape.dto.UserDto;
import com.tinkoffacademy.landscape.entity.*;
import com.tinkoffacademy.landscape.repository.AccountTypeRepository;
import com.tinkoffacademy.landscape.repository.FieldRepository;
import com.tinkoffacademy.landscape.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final FieldRepository fieldRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        configureUserDtoToUserPostConverter(modelMapper);

        configureGardenerDtoToGardenerPostConverter(modelMapper);
        configureOrderToOrderDtoMapping(modelMapper);
        configureOrderDtoToOrderMapping(modelMapper);
        configureAccountDtoToAccountMapping(modelMapper);
        configureAccountToAccountDtoMapping(modelMapper);

        modelMapper.typeMap(UserDto.class, User.class).includeBase(AccountDto.class, Account.class);
        modelMapper.typeMap(GardenerDto.class, Gardener.class).includeBase(AccountDto.class, Account.class);
        return modelMapper;
    }

    private void configureAccountDtoToAccountMapping(ModelMapper modelMapper) {

        Converter<String, AccountType> stringToAccountTypeConverter = context -> accountTypeRepository
                .findByName(context.getSource())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "AccountType with name " + context.getSource() + " not found")
                );
        modelMapper.typeMap(AccountDto.class, Account.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(stringToAccountTypeConverter)
                        .map(AccountDto::getType, Account::setType)
                );
    }

    private void configureAccountToAccountDtoMapping(ModelMapper modelMapper) {

        Converter<AccountType, String> accountTypeToStringConverter = context -> context.getSource().getName();
        modelMapper.typeMap(Account.class, AccountDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(accountTypeToStringConverter)
                        .map(Account::getType, AccountDto::setType)
                );
    }

    private void configureOrderDtoToOrderMapping(ModelMapper modelMapper) {
        Converter<Long, Field> longToFieldConverter = context -> fieldRepository.getReferenceById(context.getSource());
        modelMapper.typeMap(OrderDto.class, Order.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(longToFieldConverter)
                        .map(OrderDto::getGardenId, Order::setGarden)
                );
        Converter<Long, User> longToUserConverter = context -> userRepository.getReferenceById(context.getSource());
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

        Converter<Field, Long> orderToGardenIdConverter = context -> context.getSource().getId();
        modelMapper.typeMap(Order.class, OrderDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(orderToGardenIdConverter)
                        .map(Order::getGarden, OrderDto::setGardenId));

        Converter<User, Long> orderToUserIdConverter = context -> context.getSource().getId();
        modelMapper.typeMap(Order.class, OrderDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(orderToUserIdConverter)
                        .map(Order::getUser, OrderDto::setUserId));
    }
}
