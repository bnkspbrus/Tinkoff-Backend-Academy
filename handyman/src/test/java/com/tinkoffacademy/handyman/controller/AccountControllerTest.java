package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.config.ModelMapperConfig;
import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import com.tinkoffacademy.handyman.service.AccountService;
import com.tinkoffacademy.handyman.utils.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.tinkoff.proto.AccountServiceGrpc;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for AccountController.
 * <p>
 * Use {@link MockMvc} for testing.
 * Use {@link MockBean} for mocking {@link AccountServiceBlockingStub}.
 * Use {@link Import} for importing {@link AccountService}.
 * Use {@link WebMvcTest} for testing only {@link AccountController}.
 * Use {@link AccountControllerTest#accountDtoList} for testing.
 * Use {@link MockBean} for mocking {@link AccountRepository}
 * </p>
 */
@WebMvcTest(AccountController.class)
@Import({
        AccountService.class,
        AccountMapper.class,
        ModelMapperConfig.class
})
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountServiceBlockingStub accountServiceBlockingStub;
    @MockBean
    private AccountRepository accountRepository;
    /**
     * List of test AccountDto Objects of size 10. Use corresponding field types.
     * see {@link AccountDto}
     */
    private static final List<AccountDto> accountDtoList = List.of(
            new AccountDto("id1", "login1", "email1", "phone1", 1.0, 1.0, List.of("skill1")),
            new AccountDto("id2", "login2", "email2", "phone2", 2.0, 2.0, List.of("skill2")),
            new AccountDto("id3", "login3", "email3", "phone3", 3.0, 3.0, List.of("skill3")),
            new AccountDto("id4", "login4", "email4", "phone4", 4.0, 4.0, List.of("skill4")),
            new AccountDto("id5", "login5", "email5", "phone5", 5.0, 5.0, List.of("skill5")),
            new AccountDto("id6", "login6", "email6", "phone6", 6.0, 6.0, List.of("skill6")),
            new AccountDto("id7", "login7", "email7", "phone7", 7.0, 7.0, List.of("skill7")),
            new AccountDto("id8", "login8", "email8", "phone8", 8.0, 8.0, List.of("skill8")),
            new AccountDto("id9", "login9", "email9", "phone9", 9.0, 9.0, List.of("skill9")),
            new AccountDto("id10", "login10", "email10", "phone10", 10.0, 10.0, List.of("skill10"))
    );

    @Test
    void getAccountById() {

    }
}