package com.optimagrowth.gateway.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

	private static List<WebSocketSession> sessions = new ArrayList<>();
	
//	@Autowired
	private EventUnicastService unicastService;
//	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
    public MyWebSocketHandler(EventUnicastService eventUnicastService) {
        this.unicastService = eventUnicastService;
    }
	
	@Override
	public Mono<Void> handle(WebSocketSession session) {
		System.err.println("In Handleee");
		System.err.println(unicastService);
		
		Flux<WebSocketMessage> messages = session.receive()
                // .doOnNext(message -> { read message here or in the block below })
                .flatMap(message -> {
                    // or read message here
                    return unicastService.getMessages();
                })
                .flatMap(o -> {
                    return Mono.just(o);
                }).map(session::textMessage);
        return session.send(messages);
        
//		Flux<WebSocketMessage> messages = unicastService.getMessages()
//	              .flatMap(o -> {
//                  return Mono.just(o);
//               })
//              .map(session::textMessage);
//
//	    return session.send(messages);
	}
	
//    @Override
//    public Mono<Void> handle(WebSocketSession session) {
//    	
////    	if (!sessions.contains(session)) {
//    	sessions.add(session);
////    	}
//    	
//    	
//    	Flux<WebSocketMessage> output = session.receive()               
//                .doOnNext(message -> {
//                   System.err.println("Recieved Message");
//                   System.err.println(message.toString());
//                })
//                .concatWith(message -> {
//                	System.err.println("Concatenated Message");
//                    System.err.println(message.toString());
//                })
//                .map(value -> session.textMessage("Echo " + value));    
//
//    	
//    	List<String> responseList = new ArrayList<>();
//		responseList.add("Hellooo");
//		Flux<String> source = Mono.just(responseList).flatMapMany(Flux::fromIterable);
//        Mono<Void> output2 = session.send(source.map(responseData -> session.textMessage(responseData)));
//        return output2;//session.send(output);
//    }
    
	public void sendResponse(String endpoint, String responseBody) {
		System.err.println("Try to Send : " + responseBody);
		System.err.println("# of Sessions : " + sessions.size());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(endpoint, responseBody);
		
		unicastService.onNext(jsonObject.toString());
		
		for (WebSocketSession webSocketSession : sessions) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put(endpoint, responseBody);
			
//			try {
				
//			List<String> responseList = new ArrayList<>();
//			responseList.add(jsonObject.toString());
//			
//			Flux<String> source = Mono.just(responseList).flatMapMany(Flux::fromIterable);
//	        Mono<Void> output = webSocketSession.send(source.map(responseData -> webSocketSession.textMessage(responseData)));
	        
//	        handle(webSocketSession);
	        
//	        output.then();
		        
//				webSocketSession.send(output);
//				webSocketSession.sendMessage(new TextMessage(jsonObject.toString()));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			System.err.println("Sent "+ jsonObject.toString());
		}
//		System.err.println("Sent "+ responseBody);
	}
    
    //concatMap(
}