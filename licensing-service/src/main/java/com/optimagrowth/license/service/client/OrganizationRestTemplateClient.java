package com.optimagrowth.license.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class OrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public String getOrganization(){
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://organization-service//org/orgservice",
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }
}
