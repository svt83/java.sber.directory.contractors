import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.turkov.Application;
import ru.turkov.controller.ContractorController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Application.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class ContractorControllerTest {
     @Autowired
     private MockMvc mvc;

     @Autowired
     private ContractorController contractorController;

     @Test
     public void contextLoads() {
            assertThat(contractorController).isNotNull();
        }

     @Test
     public void test_search() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/contractor-list"))
                .andExpect(view().name("contractor-list"))
                .andExpect(status().isOk());
        }
    }
