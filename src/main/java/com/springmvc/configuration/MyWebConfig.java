package com.springmvc.configuration;

import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableJms
@ComponentScan(basePackages = "com.springmvc")
public class MyWebConfig {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	@Bean
    public ActiveMQConnectionFactory connectionFactory(){

	    final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
		
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("com.springmvc"));
        return connectionFactory;
    }
	
	 @Bean
	    public JmsTemplate jmsTemplate(){
		    final String ORDER_QUEUE = "order-queue";

	        JmsTemplate template = new JmsTemplate();
	        template.setConnectionFactory(connectionFactory());
	        template.setDefaultDestinationName(ORDER_QUEUE);
	        return template;
	    }
	 
	 @Bean
	    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setConnectionFactory(connectionFactory());
	        factory.setConcurrency("1-1");
	        return factory;
	    }

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

}
