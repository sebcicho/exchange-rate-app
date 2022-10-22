package com.sebcicho.datacollector;

import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(ApiUrlProperty.class)
public class ExchangeRateApiClient {

    @Autowired
    private ApiUrlProperty apiUrlProperty;

    private final RestTemplate restTemplate;

    @Autowired
    public ExchangeRateApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ExchangeRateDataDto queryApi() {
        return restTemplate.getForObject(apiUrlProperty.getUrl(), ExchangeRateDataDto.class);
    }
}
