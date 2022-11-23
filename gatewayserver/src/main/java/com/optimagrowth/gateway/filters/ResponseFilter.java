package com.optimagrowth.gateway.filters;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

//import com.optimagrowth.gateway.websocket.SocketCommunication;

import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {
 
    final Logger logger =LoggerFactory.getLogger(ResponseFilter.class);
    
    @Autowired
	FilterUtils filterUtils;
    
    // I need to make sure that can work and see the sessions registered inside, or do I need to it them static?
//    @Autowired
//    SocketCommunication socketCommunication;
 
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            	  HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            	  String correlationId = filterUtils.getCorrelationId(requestHeaders);
                  String seqid = filterUtils.getSequence(requestHeaders);
            	  logger.debug("Adding the correlation id to the outbound headers. {}", correlationId);
                  exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, correlationId);
                  exchange.getResponse().getHeaders().add(FilterUtils.MY_HEADER, seqid);
                  logger.debug("Completing outgoing request for {}.", exchange.getRequest().getURI());
                  
                  String endpoint = getEndpoint(exchange);
	          		Optional<List<String>> endpoints = CustomerContract.getInstance().getMappedEndpoints(endpoint);
	          		
	          		// If it is mapped to Micro-communication then this response need to be sent via socket, because it should be immediately answered before
	          		if (endpoints.isPresent()) {
	          			String firstEndpoint = endpoints.get().get(0);
	          			// need to make sure that will return the response body
//	          			socketCommunication.sendResponse(firstEndpoint, exchange.getResponse().bufferFactory().toString());
	          		}
          		
              }));
        };
    }
    
    private String getEndpoint(ServerWebExchange exchange) {
		return filterUtils.getEndpoint(exchange);
	}

}
