package com.sub;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }



    /**
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            String account = "05103015080000000503400006";
            Reguest reguest = restTemplate.getForObject(
                    "https://wl-api.mf.gov.pl/api/search/bank-account/"+ account +"?date=2020-08-03", Reguest.class);
            log.info(reguest.toString());
            System.out.println("-----------------------");
            System.out.println("miasto: " + reguest.getResult().getRequestDateTime());
            System.out.println("-----------------------");
            String nazwa = reguest.getResult().getSubjects().get(0).getName();
            System.out.println("nazwa: " + nazwa);

            Response response = new Response();
            response.setNumberChecked(account);
            response.setName(reguest.getResult().getSubjects().get(0).getName());

            responseService.addResponse(response);
        };


    }
  */
}
