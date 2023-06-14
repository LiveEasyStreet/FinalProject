package com.liveeasystreet.ecovalue;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class RecaptchaConfig {
    private String site;
    private String secret;
    private String url;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}