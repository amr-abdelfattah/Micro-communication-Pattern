package com.optimagrowth.gateway.filters;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


@Component
public class FilterUtils {

	public static final String CORRELATION_ID = "tmx-correlation-id";
//	public static final String AUTH_TOKEN     = "tmx-auth-token";
//	public static final String USER_ID        = "tmx-user-id";
//	public static final String ORG_ID         = "tmx-org-id";
//	public static final String PRE_FILTER_TYPE = "pre";
//	public static final String POST_FILTER_TYPE = "post";
//	public static final String ROUTE_FILTER_TYPE = "route";
	public static final String MY_HEADER = "custom-header";
	public static final String CLIENT_TYPE = "client-type";

	public String getCorrelationId(HttpHeaders requestHeaders){
		if (requestHeaders.get(CORRELATION_ID) !=null) {
			List<String> header = requestHeaders.get(CORRELATION_ID);
			return header.stream().findFirst().get();
		}
		else{

			return null;
		}
	}
	
	public String getClientType(HttpHeaders requestHeaders){
		if (requestHeaders.get(CLIENT_TYPE) !=null) {
			List<String> header = requestHeaders.get(CLIENT_TYPE);
			return header.stream().findFirst().get();
		}
		else{

			return null;
		}
	}

	public String getSequence(HttpHeaders requestHeaders){
		if (requestHeaders.get(MY_HEADER) !=null) {
			List<String> header = requestHeaders.get(MY_HEADER);
			return header.stream().findFirst().get();
		}
		else{

			return null;
		}
	}


	public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
		return exchange.mutate().request(
							exchange.getRequest().mutate()
							.header(name, value)
							.build())
						.build();	
	}
	
	public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
		return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
	}

	public ServerWebExchange setSequence(ServerWebExchange exchange, String seq) {
		return this.setRequestHeader(exchange, MY_HEADER, seq);
	}
	
	public ServerWebExchange setEndpoint(ServerWebExchange exchange, String endpoint) {
		return exchange.mutate().request(
				exchange.getRequest().mutate()
				.path(endpoint)
				.build())
			.build();
	}
	
	public String getEndpoint(ServerWebExchange exchange) {
		return exchange.getRequest().getPath().toString();
	}






}
