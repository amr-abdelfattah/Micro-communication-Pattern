package com.optimagrowth.gateway;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;
import org.springframework.web.reactive.function.client.WebClient;

import com.optimagrowth.gateway.websocket.MyWebSocketHandler;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableDiscoveryClient
@EnableScheduling
public class ApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerApplication.class, args);
	}
	
	 @Bean
	 @LoadBalanced
	 public WebClient.Builder loadBalancedWebClientBuilder() {
	     return WebClient.builder();
	 }
	 
//	 @Bean
//	 public MyWebSocketHandler websocketHandler() {
//		 return new MyWebSocketHandler();
//	 }
	
//	@LoadBalanced
//	@Bean
//	public RestTemplate getRestTemplate(){
//		RestTemplate template = new RestTemplate();
//        List interceptors = template.getInterceptors();
//        if (interceptors==null){
//            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
//        }
//        else{
//            interceptors.add(new UserContextInterceptor());
//            template.setInterceptors(interceptors);
//        }

//        return template;
//	}

	
//	@Bean
//	public WebFluxConfigurer serverCodecConfigurer() {
//	   return new WebFluxConfigurerComposite();
//	}
	
//	@Bean
//	public ServerCodecConfigurer serverCodecConfigurer() {
//	   return ServerCodecConfigurer.create();
//	}

}
