package beans.configuration;

import beans.models.FundType;
import beans.models.Strategy;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:data/aggressiveStrategy.properties",
        "classpath:data/balancedStrategy.properties",
        "classpath:data/safeStrategy.properties"})
public class StrategyConfiguration {

    @Value("${aggressive.strategy.polish}")
    private String aggressivePolishStrategy;

    @Value("${aggressive.strategy.foreign}")
    private String aggressiveForeignStrategy;

    @Value("${aggressive.strategy.cash}")
    private String aggressiveCashStrategy;

    @Value("${safe.strategy.polish}")
    private String safePolishStrategy;

    @Value("${safe.strategy.foreign}")
    private String safeForeignStrategy;

    @Value("${safe.strategy.cash}")
    private String safeCashStrategy;

    @Value("${balanced.strategy.polish}")
    private String balancedPolishStrategy;

    @Value("${balanced.strategy.foreign}")
    private String balancedForeignStrategy;

    @Value("${balanced.strategy.cash}")
    private String balancedCashStrategy;


    @Bean
    public Strategy aggressive() {
        return new Strategy(ImmutableMap.of(
                FundType.POLISH.value(), aggressivePolishStrategy,
                FundType.FOREIGN.value(), aggressiveForeignStrategy,
                FundType.CASH.value(), aggressiveCashStrategy
        ));
    }

    @Bean
    public Strategy safe() {
        return new Strategy(ImmutableMap.of(
                FundType.POLISH.value(), safePolishStrategy,
                FundType.FOREIGN.value(), safeForeignStrategy,
                FundType.CASH.value(), safeCashStrategy
        ));
    }

    @Bean
    public Strategy balanced() {
        return new Strategy(ImmutableMap.of(
                FundType.POLISH.value(), balancedPolishStrategy,
                FundType.FOREIGN.value(), balancedForeignStrategy,
                FundType.CASH.value(), balancedCashStrategy
        ));
    }
}
