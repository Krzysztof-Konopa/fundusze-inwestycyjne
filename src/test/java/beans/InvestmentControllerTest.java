package beans;

import beans.controllers.InvestmentController;
import beans.dtos.InvestmentDTO;
import beans.services.InvestmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest(classes = InvestmentController.class)
public class InvestmentControllerTest {

    private static final String INPUT_INVESTMENT_JSON = "{\"cash\":\"10001\",\"strategyName\":\"safe\"," +
            "\"funds\":[{\"id\":1,\"type\":\"Polish\",\"name\":\"Polski 1\"}," +
            "{\"id\":2,\"type\":\"Polish\",\"name\":\"Polski 2\"}," +
            "{\"id\":3,\"type\":\"Foreign\",\"name\":\"Zagraniczny 1\"}," +
            "{\"id\":4,\"type\":\"Foreign\",\"name\":\"Zagraniczny 2\"}," +
            "{\"id\":5,\"type\":\"Foreign\",\"name\":\"Zagraniczny 3\"},{" +
            "\"id\":6,\"type\":\"Cash\",\"name\":\"Pieniezny 1\"}]}";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private InvestmentService investmentService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testComputeInvestment() throws Exception {
        mockMvc.perform(post("/investment")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content((INPUT_INVESTMENT_JSON)))
                .andExpect(status().isOk());

        verify(investmentService, times(1)).compute(any(InvestmentDTO.class));
    }
}
