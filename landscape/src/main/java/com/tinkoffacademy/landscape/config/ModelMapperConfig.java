package com.tinkoffacademy.landscape.config;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.proto.AccountCredProto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Account.class, AccountCredProto.Builder.class)
                .setProvider(new AbstractProvider<>() {
                                 @Override
                                 protected AccountCredProto.Builder get() {
                                     return AccountCredProto.newBuilder();
                                 }
                             }
                );
        modelMapper.typeMap(AccountDto.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        return modelMapper;
    }
}
