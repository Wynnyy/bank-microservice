package sk.zentity.restcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import sk.zentity.account.AccountRepository;
import sk.zentity.model.dto.TransferDto;
import sk.zentity.model.entities.Account;
import sk.zentity.model.entities.Transfer;
import sk.zentity.transfer.TransferRepository;
import sk.zentity.transfer.TransferService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.zentity.transfer.TransferRestController.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TransferRestControllerTest {

    final String DEBTOR_IBAN = "SK3155000000198742637547";
    final String CREDITOR_IBAN = "SK3112000000198742637541";
    final BigDecimal AMOUNT = new BigDecimal(100);
    final String MESSAGE = "for you";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferService transferService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testTransferMoney() throws Exception {
        TransferDto transferDto = new TransferDto(1L, LocalDateTime.now(), AMOUNT, DEBTOR_IBAN, CREDITOR_IBAN, MESSAGE, 1L);

        Account account = accountRepository.findByIban(DEBTOR_IBAN);

        Mockito.when(transferService.transferCredit(CREDITOR_IBAN, DEBTOR_IBAN, AMOUNT, MESSAGE)).thenReturn(transferDto);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(TRANSFER + TRANSFER_CREDIT)
                        .param("creditorIban", CREDITOR_IBAN)
                        .param("debtorIban", DEBTOR_IBAN)
                        .param("amount", "100")
                        .param("message", MESSAGE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.date").isNotEmpty())
                .andExpect(jsonPath("$.amount").value("100"))
                .andExpect(jsonPath("$.debtorIban").value("SK3155000000198742637547"))
                .andExpect(jsonPath("$.creditorIban").value("SK3112000000198742637541"))
                .andExpect(jsonPath("$.message").value("for you"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(objectMapper.writeValueAsString(transferDto), result);

        //check whether money was transfered correctly
        Account accountAfterTransfer = accountRepository.findByIban(transferDto.getDebtorIban());

        //if balance is compared, it's same, then return 1(true), expected is 1(true)
        assertEquals(1, account.getBalance().add((transferDto.getAmount())).compareTo(accountAfterTransfer.getBalance()));
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testTransferViewHistory() throws Exception {
        //save testing transfer for test
        Transfer transfer = new Transfer(1L, null, AMOUNT, DEBTOR_IBAN, CREDITOR_IBAN, MESSAGE, 1L);
        transferRepository.save(transfer);

        List<Transfer> transferList = transferRepository.findAll();

        Mockito.when(transferService.getTransitions(1L)).thenReturn(
                List.of(new TransferDto(1L, null, new BigDecimal(100.0), DEBTOR_IBAN, CREDITOR_IBAN, MESSAGE, 1L)));


        String result = mockMvc.perform(MockMvcRequestBuilders.get(TRANSFER + VIEW_HISTORY)
                        .param("account", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].amount").value(100.0))
                .andExpect(jsonPath("$.[0].debtorIban").value(DEBTOR_IBAN))
                .andExpect(jsonPath("$.[0].creditorIban").value(CREDITOR_IBAN))
                .andExpect(jsonPath("$.[0].message").value("for you"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);

        //result is 100.0 , post result is 100, set bellow avoid will avoid this problem
        transferList.get(0).setAmount(AMOUNT);

        assertEquals(objectMapper.writeValueAsString(transferList), result);
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testTransferSpecificSelect() throws Exception {
        //save testing transfer for test
        Transfer transfer = new Transfer(1L, null, AMOUNT, DEBTOR_IBAN, CREDITOR_IBAN, MESSAGE, 1L);
        transferRepository.save(transfer);

        List<Transfer> transferList = transferRepository.findAll();

        Mockito.when(transferService.getSpecificTransitions(AMOUNT, DEBTOR_IBAN, MESSAGE, 1L)).thenReturn(
                List.of(new TransferDto(1L, null, new BigDecimal(100), DEBTOR_IBAN, CREDITOR_IBAN, MESSAGE, 1L)));


        String result = mockMvc.perform(MockMvcRequestBuilders.get(TRANSFER + SPECIFIC_TRANSITIONS)
                        .param("amount", "100")
                        .param("debtorIban", DEBTOR_IBAN)
                        .param("message", MESSAGE)
                        .param("accountId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].amount").value(100.0))
                .andExpect(jsonPath("$.[0].debtorIban").value(DEBTOR_IBAN))
                .andExpect(jsonPath("$.[0].creditorIban").value(CREDITOR_IBAN))
                .andExpect(jsonPath("$.[0].message").value("for you"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);

        //result is 100.0 , post result is 100, set bellow avoid will avoid this problem
        transferList.get(0).setAmount(AMOUNT);

        assertEquals(objectMapper.writeValueAsString(transferList), result);
    }


}
