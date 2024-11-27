package sk.zentity.restcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import sk.zentity.account.AccountRepository;
import sk.zentity.account.AccountService;
import sk.zentity.model.dto.AccountDto;
import sk.zentity.model.entities.Account;
import sk.zentity.model.enums.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.zentity.account.AccountRestController.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountRestControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testCreateAccount() throws Exception {
        Account account = new Account(1L, "SK3112000000198742637541", Currency.EUR, new BigDecimal(4557.20), 1L, new ArrayList<>());
        AccountDto accountDto = new AccountDto(1L, "SK3112000000198742637541", Currency.EUR, new BigDecimal(4557.20), 1L, new ArrayList<>());

        Mockito.when(accountService.create(account)).thenReturn(accountDto);


        String result = mockMvc.perform(MockMvcRequestBuilders.post(ACCOUNT + CREATE_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.iban").value("SK3112000000198742637541"))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andExpect(jsonPath("$.balance").value(4557.20))
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.transfers").exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(objectMapper.writeValueAsString(accountDto), result);
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testModifyAccount() throws Exception {
        AccountDto updatedAccount = new AccountDto(1L, "SK3112000000198742637541", Currency.EUR, new BigDecimal(9999), 1L, new ArrayList<>());

        Account account = accountRepository.findById(1L).orElseThrow();
        account.setTransfer(new ArrayList<>());
        account.setBalance(new BigDecimal(9999));

        Mockito.when(accountService.modify(account)).thenReturn(updatedAccount);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(ACCOUNT + MODIFY_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.iban").value("SK3112000000198742637541"))
                .andExpect(jsonPath("$.currency").value("EUR"))
                .andExpect(jsonPath("$.balance").value(9999))
                .andExpect(jsonPath("$.customerId").value(1L))
                .andExpect(jsonPath("$.transfers").exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(objectMapper.writeValueAsString(updatedAccount), result);
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testDeleteCustomer() throws Exception {
        Account account = accountRepository.findById(1L).orElseThrow();

        String expectedResult = "Account with ID " + account.getId() + " successfully deleted.";

        String result = mockMvc.perform(MockMvcRequestBuilders.delete(ACCOUNT)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }
}
