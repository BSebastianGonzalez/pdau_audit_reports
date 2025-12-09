package com.pdau.ar.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String AUDITORIA_EXCHANGE = "auditoria.respuesta.exchange";
    public static final String AUDITORIA_ROUTING_KEY = "auditoria.respuesta.creada";
    public static final String AUDITORIA_QUEUE = "auditoria.respuesta.queue";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue auditoriaQueue() {
        return new Queue(AUDITORIA_QUEUE, true);
    }

    @Bean
    public TopicExchange auditoriaExchange() {
        return new TopicExchange(AUDITORIA_EXCHANGE);
    }

    @Bean
    public Binding auditoriaBinding() {
        return BindingBuilder.bind(auditoriaQueue())
                .to(auditoriaExchange())
                .with(AUDITORIA_ROUTING_KEY);
    }
}

