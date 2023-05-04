package com.tinkoffacademy.landscape.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.repository.AccountTypeV2Repository;
import com.tinkoffacademy.landscape.service.AccountService;
import com.tinkoffacademy.landscape.service.AccountTypeV2Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class, properties = "spring.datasource.url=none")
@Import({AccountService.class, AccountTypeV2Service.class})
class AccountControllerTest {

    private static final List<AccountTypeV2> types = List.of(
            new AccountTypeV2(1, "handyman"),
            new AccountTypeV2(2, "rancher")
    );

    private static final List<Account> accounts = List.of(
            new Account(
                    UUID.randomUUID(),
                    getTypes().get(0),
                    "Samuel Bush",
                    "pjones@example.com",
                    "886-803-4011x219",
                    LocalDateTime.parse("2023-01-04T15:15:31"),
                    LocalDateTime.parse("2023-02-23T01:19:23"),
                    0D,
                    0D
            ),
            new Account(
                    UUID.randomUUID(),
                    getTypes().get(0),
                    "Michael Roberts",
                    "smithnicole@example.net",
                    "(548)115-8479x41804",
                    LocalDateTime.parse("2022-05-03T01:51:01"),
                    LocalDateTime.parse("2022-09-20T15:24:02"),
                    2D,
                    3D
            ),
            new Account(
                    UUID.randomUUID(),
                    getTypes().get(0),
                    "Daniel Myers",
                    "brittany21@example.com",
                    "652.709.1771",
                    LocalDateTime.parse("2022-03-24T01:58:21"),
                    LocalDateTime.parse("2022-11-13T02:31:27"),
                    3D,
                    5D
            )
    );

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountTypeV2Repository accountTypeV2Repository;

    private static List<AccountTypeV2> getTypes() {
        return types;
    }

    private static List<Account> getAccounts() {
        return accounts;
    }

    @Test
    void testFindAll() throws Exception {
        when(accountRepository.findAll()).thenReturn(getAccounts());
        mockMvc.perform(get("/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(getAccounts())));
    }

    @ParameterizedTest
    @MethodSource("getAccounts")
    void testFindById(Account account) throws Exception {
        when(accountRepository.findById(account.getId()))
                .thenReturn(Optional.of(account));
        mockMvc.perform(get("/accounts/" + account.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(account)));
    }
}