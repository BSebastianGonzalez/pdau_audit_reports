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

    public static final String APELACION_EXCHANGE = "auditoria.apelacion.exchange";
    public static final String APELACION_ROUTING_KEY = "auditoria.apelacion.creada";
    public static final String APELACION_QUEUE = "auditoria.apelacion.queue";

    public static final String RESPUESTA_APELACION_EXCHANGE = "auditoria.respuesta_apelacion.exchange";
    public static final String RESPUESTA_APELACION_ROUTING_KEY = "auditoria.respuesta_apelacion.creada";
    public static final String RESPUESTA_APELACION_QUEUE = "auditoria.respuesta_apelacion.queue";

    public static final String COMENTARIO_EXCHANGE = "auditoria.comentario.exchange";
    public static final String COMENTARIO_ROUTING_KEY = "auditoria.comentario.creado";
    public static final String COMENTARIO_QUEUE = "auditoria.comentario.queue";

    public static final String ARCHIVAMIENTO_EXCHANGE = "auditoria.archivamiento.exchange";
    public static final String ARCHIVAMIENTO_ROUTING_KEY = "auditoria.archivamiento.creado";
    public static final String ARCHIVAMIENTO_QUEUE = "auditoria.archivamiento.queue";

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

    @Bean
    public Queue apelacionQueue() {
        return new Queue(APELACION_QUEUE, true);
    }

    @Bean
    public TopicExchange apelacionExchange() {
        return new TopicExchange(APELACION_EXCHANGE);
    }

    @Bean
    public Binding apelacionBinding() {
        return BindingBuilder.bind(apelacionQueue())
                .to(apelacionExchange())
                .with(APELACION_ROUTING_KEY);
    }

    @Bean
    public Queue respuestaApelacionQueue() {
        return new Queue(RESPUESTA_APELACION_QUEUE, true);
    }

    @Bean
    public TopicExchange respuestaApelacionExchange() {
        return new TopicExchange(RESPUESTA_APELACION_EXCHANGE);
    }

    @Bean
    public Binding respuestaApelacionBinding() {
        return BindingBuilder.bind(respuestaApelacionQueue())
                .to(respuestaApelacionExchange())
                .with(RESPUESTA_APELACION_ROUTING_KEY);
    }

    @Bean
    public Queue comentarioQueue() {
        return new Queue(COMENTARIO_QUEUE, true);
    }

    @Bean
    public Queue archivamientoQueue() {
        return new Queue(ARCHIVAMIENTO_QUEUE, true);
    }

    @Bean
    public TopicExchange comentarioExchange() {
        return new TopicExchange(COMENTARIO_EXCHANGE);
    }

    @Bean
    public TopicExchange archivamientoExchange() {
        return new TopicExchange(ARCHIVAMIENTO_EXCHANGE);
    }

    @Bean
    public Binding comentarioBinding() {
        return BindingBuilder.bind(comentarioQueue())
                .to(comentarioExchange())
                .with(COMENTARIO_ROUTING_KEY);
    }

    @Bean
    public Binding archivamientoBinding() {
        return BindingBuilder.bind(archivamientoQueue())
                .to(archivamientoExchange())
                .with(ARCHIVAMIENTO_ROUTING_KEY);
    }
}

