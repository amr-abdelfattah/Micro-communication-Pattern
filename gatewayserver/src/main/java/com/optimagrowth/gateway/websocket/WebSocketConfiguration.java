//package com.optimagrowth.gateway.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic/", "/queue/");
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/greeting");
//    }
//
//}
////
////@Configuration
////@EnableWebSocket
////public class WebSocketConfiguration implements WebSocketConfigurer {
////
////	@Autowired
////	SocketCommunication socketCommunication;
////	
////	@Bean
////	public ServletServerContainerFactoryBean createWebSocketContainer() {
////	    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
////	    container.setMaxBinaryMessageBufferSize(1024000);
////	    return container;
////	}
////	
////	@Override
////	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
////		registry.addHandler(socketCommunication, "/receive").setAllowedOrigins("*");
////	}
////
////}
