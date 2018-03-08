package beans;

import beans.configuration.InvestmentServiceApplication;
import beans.dtos.InvestmentDTO;
import beans.models.Fund;
import beans.models.FundType;
import beans.models.Investment;
import beans.models.Result;
import beans.services.InvestmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InvestmentServiceApplication.class})
public class InvestmentServiceTest {

    @Autowired
    private InvestmentService investmentService;

    private static final String INPUT_INVESTMENT_JSON = "{\"cash\":\"10001\",\"strategyName\":\"safe\"," +
            "\"funds\":[{\"id\":1,\"type\":\"Polish\",\"name\":\"Polski 1\"}," +
            "{\"id\":2,\"type\":\"Polish\",\"name\":\"Polski 2\"}," +
            "{\"id\":3,\"type\":\"Foreign\",\"name\":\"Zagraniczny 1\"}," +
            "{\"id\":4,\"type\":\"Foreign\",\"name\":\"Zagraniczny 2\"}," +
            "{\"id\":5,\"type\":\"Foreign\",\"name\":\"Zagraniczny 3\"},{" +
            "\"id\":6,\"type\":\"Cash\",\"name\":\"Pieniezny 1\"}]}";

    @Test
    public void testCompute() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        InvestmentDTO investmentDTO = objectMapper.readValue(INPUT_INVESTMENT_JSON, InvestmentDTO.class);

        Investment investment = investmentService.compute(investmentDTO);

        assertThat(investment.getRemainCash()).isEqualTo(1L);
        assertThat(investment.getResults()).isNotNull();
        assertThat(investment.getResults()).isNotEmpty();
        assertThat(investment.getResults().size()).isEqualTo(6);

        Fund fund = new Fund();
        fund.setId(1L);
        fund.setName("Polski 1");
        fund.setType(FundType.POLISH.value());
        Result result = new Result(fund, "1000", "10.00");

        assertThat(investment.getResults().get(0)).isEqualToComparingFieldByFieldRecursively(result);
    }

    @Test
    public void testComputeEdgeCases() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        for(int i = 2; i < 20; i++) {
            String jsonInput = TestUtil.readTestCase(this.getClass(), "input/edgeCaseInput" + i +".json");
            String jsonOutput = TestUtil.readTestCase(this.getClass(), "output/edgeCaseOutput" + i +".json");

            InvestmentDTO investmentDTO = objectMapper.readValue(jsonInput, InvestmentDTO.class);
            Investment investment = objectMapper.readValue(jsonOutput, Investment.class);

            Investment investmentComputed = investmentService.compute(investmentDTO);

            assertThat(investmentComputed).isEqualToComparingFieldByFieldRecursively(investment);
        }
    }
}
