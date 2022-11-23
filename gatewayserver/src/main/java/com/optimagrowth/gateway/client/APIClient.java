package com.optimagrowth.gateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


//@Component
//public class APIClient {
//    @Autowired
//    RestTemplate restTemplate;
//
//    public String consume(String endpoint){
////        ResponseEntity<String> restExchange =
////                restTemplate.exchange(
////                        "http://organization-service//org/orgservice",
////                        HttpMethod.GET,
////                        null, String.class);
//    	
//    	 ResponseEntity<String> restExchange =
//                 restTemplate.exchange(
//                         endpoint,
//                         HttpMethod.GET,
//                         null, String.class);
//
//        return restExchange.getBody();
//    }
//}
