package sk.app.restcontroller;


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
import sk.app.customer.CustomerRepository;
import sk.app.customer.CustomerService;
import sk.app.model.dto.CustomerDto;
import sk.app.model.entities.Customer;
import sk.app.model.enums.Sex;
import sk.app.model.enums.States;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.app.customer.CustomerRestController.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerRestControllerTest {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testCreateCustomer() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(1900, 06, 05, 0, 0);

        Customer customer = new Customer(1L, "customer", "name", "surName", Sex.MALE, States.AFGHANISTAN, localDateTime, 575321,
                localDateTime, localDateTime, new ArrayList<>());
        CustomerDto customerDto = new CustomerDto(1L, "customer", "name", "surName", Sex.MALE, States.AFGHANISTAN, localDateTime, 575321,
                localDateTime, localDateTime, new ArrayList<>());
        Mockito.when(customerService.create(customer)).thenReturn(customerDto);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER + CREATE_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customer").value("customer"))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.surName").value("surName"))
                .andExpect(jsonPath("$.sex").value("MALE"))
                .andExpect(jsonPath("$.nationality").value("AFGHANISTAN"))
                .andExpect(jsonPath("$.dateOfBirth").isNotEmpty())
                .andExpect(jsonPath("$.cardNumber").value(575321))
                .andExpect(jsonPath("$.dateOfIssue").isNotEmpty())
                .andExpect(jsonPath("$.dateOfExpiry").isNotEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(objectMapper.writeValueAsString(customerDto), result);
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testModifyCustomer() throws Exception {
        CustomerDto updatedCustomer = new CustomerDto(1L, "classic", "updatedPeter", "Sladecek", Sex.MALE, States.SLOVAKIA, LocalDateTime.of(1990, 06, 10, 0, 0), 457576756,
                LocalDateTime.of(2022, 01, 01, 0, 0), LocalDateTime.of(2027, 01, 01, 0, 0), new ArrayList<>());

        Customer customer = customerRepository.findById(1L).orElseThrow();
        customer.setAccounts(new ArrayList<>());
        customer.setName("updatedPeter");

        Mockito.when(customerService.modify(customer)).thenReturn(updatedCustomer);

        String result = mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER + MODIFY_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.customer").value("classic"))
                .andExpect(jsonPath("$.name").value("updatedPeter"))
                .andExpect(jsonPath("$.surName").value("Sladecek"))
                .andExpect(jsonPath("$.sex").value("MALE"))
                .andExpect(jsonPath("$.nationality").value("SLOVAKIA"))
                .andExpect(jsonPath("$.dateOfBirth").isNotEmpty())
                .andExpect(jsonPath("$.cardNumber").value(457576756))
                .andExpect(jsonPath("$.dateOfIssue").isNotEmpty())
                .andExpect(jsonPath("$.dateOfExpiry").isNotEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);
        assertEquals(objectMapper.writeValueAsString(updatedCustomer), result);
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void testDeleteCustomer() throws Exception {
        Customer customer = customerRepository.findById(1L).orElseThrow();

        String expectedResult = "Customer with ID " + customer.getId() + " successfully deleted.";

        String result = mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER)
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
