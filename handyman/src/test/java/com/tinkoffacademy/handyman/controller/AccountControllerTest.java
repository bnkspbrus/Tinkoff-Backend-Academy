package com.tinkoffacademy.handyman.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinkoffacademy.handyman.config.ModelMapperConfig;
import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.document.Account;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import com.tinkoffacademy.handyman.service.AccountService;
import com.tinkoffacademy.handyman.utils.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.IdProto;

import java.util.List;
import java.util.stream.Stream;

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
    @Mock
    private AccountServiceBlockingStub landscapeStub;
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountMapper accountMapper;

    /**
     * List of test AccountDto Objects of size 10. Use corresponding field types.
     * see {@link AccountDto}
     */
    @BeforeEach
    void injectStub() {
        accountService.setLandscapeStub(landscapeStub);
        accountMapper.setLandscapeStub(landscapeStub);
    }

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
    private static final List<Account> accountList = List.of(
            new Account("id1", 1L, 1.0, 1.0, List.of("skill1")),
            new Account("id2", 2L, 2.0, 2.0, List.of("skill2")),
            new Account("id3", 3L, 3.0, 3.0, List.of("skill3")),
            new Account("id4", 4L, 4.0, 4.0, List.of("skill4")),
            new Account("id5", 5L, 5.0, 5.0, List.of("skill5")),
            new Account("id6", 6L, 6.0, 6.0, List.of("skill6")),
            new Account("id7", 7L, 7.0, 7.0, List.of("skill7")),
            new Account("id8", 8L, 8.0, 8.0, List.of("skill8")),
            new Account("id9", 9L, 9.0, 9.0, List.of("skill9")),
            new Account("id10", 10L, 10.0, 10.0, List.of("skill10"))
    );
    /**
     * List of test IdProto Objects of size 10. Use IdProto.newBuilder().build() for creating. Use setValue() for setting value.
     * see {@link IdProto}
     */
    private static final List<IdProto> uuidProtoList = List.of(
            IdProto.newBuilder().setValue(1L).build(),
            IdProto.newBuilder().setValue(2L).build(),
            IdProto.newBuilder().setValue(3L).build(),
            IdProto.newBuilder().setValue(4L).build(),
            IdProto.newBuilder().setValue(5L).build(),
            IdProto.newBuilder().setValue(6L).build(),
            IdProto.newBuilder().setValue(7L).build(),
            IdProto.newBuilder().setValue(8L).build(),
            IdProto.newBuilder().setValue(9L).build(),
            IdProto.newBuilder().setValue(10L).build()
    );
    /**
     * List of test AccountCredProto Objects of size 10. Use AccountCredProto.newBuilder().build() for creating. Use setLogin(), setEmail() and setPhone() for setting value.
     * Login, email and phone should match the corresponding fields of {@link AccountDto} in {@link AccountControllerTest#accountDtoList}.
     * see {@link AccountCredProto}
     */
    private static final List<AccountCredProto> accountCredProtoList = List.of(
            AccountCredProto.newBuilder().setLogin("login1").setEmail("email1").setPhone("phone1").build(),
            AccountCredProto.newBuilder().setLogin("login2").setEmail("email2").setPhone("phone2").build(),
            AccountCredProto.newBuilder().setLogin("login3").setEmail("email3").setPhone("phone3").build(),
            AccountCredProto.newBuilder().setLogin("login4").setEmail("email4").setPhone("phone4").build(),
            AccountCredProto.newBuilder().setLogin("login5").setEmail("email5").setPhone("phone5").build(),
            AccountCredProto.newBuilder().setLogin("login6").setEmail("email6").setPhone("phone6").build(),
            AccountCredProto.newBuilder().setLogin("login7").setEmail("email7").setPhone("phone7").build(),
            AccountCredProto.newBuilder().setLogin("login8").setEmail("email8").setPhone("phone8").build(),
            AccountCredProto.newBuilder().setLogin("login9").setEmail("email9").setPhone("phone9").build(),
            AccountCredProto.newBuilder().setLogin("login10").setEmail("email10").setPhone("phone10").build()
    );

    /**
     * Provide a stream of {@link Arguments} to be injected into {@link AccountControllerTest#getAccountById(AccountDto, Account, AccountCredProto, IdProto)}
     * Use {@link Arguments#of(Object...)} for creating {@link Arguments} instance.
     * Use corresponding objects from {@link AccountControllerTest#accountDtoList}, {@link AccountControllerTest#accountList}, {@link AccountControllerTest#accountCredProtoList} and {@link AccountControllerTest#uuidProtoList}
     *
     * @return a stream of {@link Arguments}
     */
    private static Stream<Arguments> provideGetAccountById() {
        return Stream.of(
                Arguments.of(accountDtoList.get(0), accountList.get(0), accountCredProtoList.get(0), uuidProtoList.get(0)),
                Arguments.of(accountDtoList.get(1), accountList.get(1), accountCredProtoList.get(1), uuidProtoList.get(1)),
                Arguments.of(accountDtoList.get(2), accountList.get(2), accountCredProtoList.get(2), uuidProtoList.get(2)),
                Arguments.of(accountDtoList.get(3), accountList.get(3), accountCredProtoList.get(3), uuidProtoList.get(3)),
                Arguments.of(accountDtoList.get(4), accountList.get(4), accountCredProtoList.get(4), uuidProtoList.get(4)),
                Arguments.of(accountDtoList.get(5), accountList.get(5), accountCredProtoList.get(5), uuidProtoList.get(5)),
                Arguments.of(accountDtoList.get(6), accountList.get(6), accountCredProtoList.get(6), uuidProtoList.get(6)),
                Arguments.of(accountDtoList.get(7), accountList.get(7), accountCredProtoList.get(7), uuidProtoList.get(7)),
                Arguments.of(accountDtoList.get(8), accountList.get(8), accountCredProtoList.get(8), uuidProtoList.get(8)),
                Arguments.of(accountDtoList.get(9), accountList.get(9), accountCredProtoList.get(9), uuidProtoList.get(9))
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetAccountById")
    void getAccountById(AccountDto accountDto, Account account, AccountCredProto accountCredProto, IdProto uuidProto) throws Exception {
        Mockito.when(accountRepository.findById(account.getId())).thenReturn(java.util.Optional.of(account));
        Mockito.when(landscapeStub.findById(uuidProto)).thenReturn(accountCredProto);
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/" + account.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(accountDto)));
        Mockito.verify(accountRepository, Mockito.times(1)).findById(account.getId());
        Mockito.verify(landscapeStub, Mockito.times(1)).findById(uuidProto);
    }
}