package org.serguei3000.task.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {

	//serg3000 в скрипте: var socket = new new SockJS('/websocketApp');   stompClient = Stomp.over(socket);
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocketApp").withSockJS();
	}

	
	//serg3000 здесь подключили RABBIT, который будет расслылать полученные на него сообщения всем участникам
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");  //serg3000 "/app" - префикс маппинга для приложения
		registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").setRelayPort(61613).setClientLogin("guest")
				.setClientPasscode("guest");    //serg3000 "/topic" - префикс маппинга для брокера 

	}
}