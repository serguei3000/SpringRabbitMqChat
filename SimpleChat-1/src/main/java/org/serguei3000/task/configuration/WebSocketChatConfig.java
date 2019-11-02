package org.serguei3000.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
	
	
	 //serg3000 без этого бина у меня приложение не видело шаблонов, не всегда нужно, зависит от версии библиотек Spring
    //@Bean
    //public ClassLoaderTemplateResolver yourTemplateResolver() {
        //ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
        //configurer.setPrefix("templates/");
        //configurer.setSuffix(".html");
        //configurer.setTemplateMode(TemplateMode.HTML);
        //configurer.setCharacterEncoding("UTF-8");
        //configurer.setOrder(0);  
        //configurer.setCacheable(false);
        //configurer.setCheckExistence(true);
       //return configurer;
    //}
}