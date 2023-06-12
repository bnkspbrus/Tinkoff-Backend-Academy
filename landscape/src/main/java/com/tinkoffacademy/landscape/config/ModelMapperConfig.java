package com.tinkoffacademy.landscape.config;

import com.tinkoffacademy.landscape.dto.*;
import com.tinkoffacademy.landscape.entity.*;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        // configure ModelMapper to skip null fields
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);
        // converter from List<Field> to List<FieldDto>
        Converter<List<FieldDto>, List<Field>> toFieldList = ctx -> ctx.getSource()
                .stream()
                .map(fieldDto -> modelMapper.map(fieldDto, Field.class))
                .toList();
        // configure ModelMapper to map GardenerDto's fields to Gardener's fields.
        modelMapper.typeMap(GardenerDto.class, Gardener.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(toFieldList)
                        .map(GardenerDto::getFields, Gardener::setFields)
                );
        // converter from List<Field> to List<FieldDto>
        Converter<List<Field>, List<FieldDto>> toFieldDtoList = ctx -> ctx.getSource()
                .stream()
                .map(field -> modelMapper.map(field, FieldDto.class))
                .toList();
        // configure ModelMapper to map Gardener's fields to GardenerDto's fields.
        modelMapper.typeMap(Gardener.class, GardenerDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(toFieldDtoList)
                        .map(Gardener::getFields, GardenerDto::setFields)
                );
        // converter from List<UserAccountDto> to List<UserAccount>
        Converter<List<UserAccountDto>, List<UserAccount>> toUserAccountList = ctx -> ctx.getSource()
                .stream()
                .map(userAccountDto -> modelMapper.map(userAccountDto, UserAccount.class))
                .toList();
        // configure ModelMapper to map UserDto's userAccounts to User's userAccounts.
        modelMapper.typeMap(UserDto.class, User.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(toUserAccountList)
                        .map(UserDto::getUserAccounts, User::setUserAccounts)
                );
        // converter from List<UserAccount> to List<UserAccountDto>
        Converter<List<UserAccount>, List<UserAccountDto>> toUserAccountDtoList = ctx -> ctx.getSource()
                .stream()
                .map(userAccount -> modelMapper.map(userAccount, UserAccountDto.class))
                .toList();
        // configure ModelMapper to map User's userAccounts to UserDto's userAccounts.
        modelMapper.typeMap(User.class, UserDto.class)
                .addMappings(mapper -> mapper
                        .when(Conditions.isNotNull())
                        .using(toUserAccountDtoList)
                        .map(User::getUserAccounts, UserDto::setUserAccounts)
                );
        return modelMapper;
    }
}
