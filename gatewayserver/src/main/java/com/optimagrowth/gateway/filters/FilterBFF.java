package com.optimagrowth.gateway.filters;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.optimagrowth.gateway.websocket.MyWebSocketHandler;

//import com.optimagrowth.gateway.websocket.SocketCommunication;

//import com.optimagrowth.gateway.client.APIClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class FilterBFF implements GlobalFilter {

	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

	@Autowired
	FilterUtils filterUtils;
	
//	@Autowired
//	APIClient apiClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MyWebSocketHandler socketCommunication;
	

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.err.println("Hereee");
		logger.debug("Helloooo");
		HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
		if (isCorrelationIdPresent(requestHeaders)) {
			logger.debug("tmx-correlation-id found in tracking filter: {}. ", 
					filterUtils.getCorrelationId(requestHeaders));
		} else {
			String correlationID = generateCorrelationId();
			exchange = filterUtils.setCorrelationId(exchange, correlationID);
			logger.debug("tmx-correlation-id generated in tracking filter: {}.", correlationID);
		}

		if (isSequence(requestHeaders)) {
			logger.debug("custom-header found in tracking filter: {}. ",
					filterUtils.getSequence(requestHeaders));
		} else {
			String seqid = "islam";
			exchange = filterUtils.setSequence(exchange, seqid);
			logger.debug("custom-header generated in tracking filter: {}.", seqid);
		}
		
		// BFF
		// No thing special
		
		
		String endpoint = getEndpoint(exchange);
		logger.debug("Called Endpoint: {}. ", endpoint);
		
		Optional<List<String>> endpoints = CustomerContract.getInstance().getMappedEndpoints(endpoint);
		
		// If it is mapped to Micro-communication then map to the new endpoint
		if (endpoints.isPresent()) {
//			String firstEndpoint = endpoints.get().get(0);
//			setEndpoint(exchange, firstEndpoint);
			for (String endpointURL : endpoints.get()) {
				logger.debug("Enspoint to call : {}.", endpointURL);
				consume(endpointURL, requestHeaders);
//				logger.debug("Resopnse of {} : {}.", endpointURL, response);
			}
			
//			return chain.filter(exchange)
//			          .then(Mono.fromCallable((exchange, chain) -> {
//			              logger.info("Last Post Global Filter");
//			              ServerHttpResponse response = exchange.getResponse();
//			              response.setStatusCode(201);
////			              response.
//			              
//			            }));
			
//			return chain;
		}
		
		return chain.filter(exchange);
	}
	
	private void consume(String endpoint, HttpHeaders requestHeaders) {
//		String response = apiClient.consume(endpoint);
		
		logger.debug("Client-Type : {}.", filterUtils.getClientType(requestHeaders));
		
		
		Flux<String> responseFlux = webClientBuilder.build()
			      .get()
			      .uri(endpoint)
			      .header(FilterUtils.CLIENT_TYPE, filterUtils.getClientType(requestHeaders))
			      .retrieve()
			      .bodyToFlux(String.class);

		responseFlux.subscribe((responseData) -> { 
			logger.debug("Resopnse of {} : {}.", endpoint, responseData);
//			socketCommunication.sendResponse(endpoint, responseData);
			socketCommunication.sendResponse(endpoint, responseData);
		});
		
//		return response;
	}
	
	private String getEndpoint(ServerWebExchange exchange) {
		return filterUtils.getEndpoint(exchange);
	}

	private void setEndpoint(ServerWebExchange exchange, String endpoint) {
		filterUtils.setEndpoint(exchange, endpoint);
	}


	private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
		if (filterUtils.getCorrelationId(requestHeaders) != null) {

			return true;
		} else {
			return false;
		}
	}

	private boolean isSequence(HttpHeaders requestHeaders) {
		if (filterUtils.getSequence(requestHeaders) != null) {

			return true;
		} else {
			return false;
		}
	}


	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

}