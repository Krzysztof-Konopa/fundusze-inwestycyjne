package beans.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@ComponentScan("beans")
public class InvestmentServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(InvestmentServiceApplication.class, args);
        System.out.println("### Investment Service Started ###");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
