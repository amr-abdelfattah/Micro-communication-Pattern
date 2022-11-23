package com.optimagrowth.gateway.websocket;

import reactor.core.publisher.Flux;

public interface EventUnicastService {
	/**
     * Add message to stream
     * @param next - message which will be added to stream
     */
    void onNext(String next);

    Flux<String> getMessages();
}
