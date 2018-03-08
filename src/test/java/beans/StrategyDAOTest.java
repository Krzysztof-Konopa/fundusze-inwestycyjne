package beans;

import beans.configuration.InvestmentServiceApplication;
import beans.daos.StrategyDAO;
import beans.models.FundType;
import beans.models.Strategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { InvestmentServiceApplication.class})
public class StrategyDAOTest {

    @Autowired
    private StrategyDAO strategyDAO;

    @Test
    public void testGetByName() {

        Strategy safeStrategy = strategyDAO.getByName("safe");
        Strategy aggressiveStrategy = strategyDAO.getByName("aggressive");
        Strategy balancedStrategy = strategyDAO.getByName("balanced");

        Map<String, String> safeVolumeMap = safeStrategy.getVolumesMap();
        Map<String, String> aggressiveVolumeMap = aggressiveStrategy.getVolumesMap();
        Map<String, String> balancedVolumeMap = balancedStrategy.getVolumesMap();


        // safe strategy
        assertThat(safeVolumeMap.get(FundType.POLISH.value())).isEqualTo("20");
        assertThat(safeVolumeMap.get(FundType.FOREIGN.value())).isEqualTo("75");
        assertThat(safeVolumeMap.get(FundType.CASH.value())).isEqualTo("5");

        // aggressive strategy
        assertThat(aggressiveVolumeMap.get(FundType.POLISH.value())).isEqualTo("40");
        assertThat(aggressiveVolumeMap.get(FundType.FOREIGN.value())).isEqualTo("20");
        assertThat(aggressiveVolumeMap.get(FundType.CASH.value())).isEqualTo("40");

        // balanced strategy
        assertThat(balancedVolumeMap.get(FundType.POLISH.value())).isEqualTo("30");
        assertThat(balancedVolumeMap.get(FundType.FOREIGN.value())).isEqualTo("60");
        assertThat(balancedVolumeMap.get(FundType.CASH.value())).isEqualTo("10");
    }
}
