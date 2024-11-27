package sk.app.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static sk.app.customer.CustomerRestController.CUSTOMER;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginWithoutCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", password = "pass")
    void loginWithCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
