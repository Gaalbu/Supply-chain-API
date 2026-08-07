package com.supplychain.supply_chain_api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfiguracaoRabbitMQ {
    public static final String NOME_FILA = "pacote.mudanca.status";
    public static final String NOME_EXCHANGE = "supply-chain.exchange";
    public static final String CHAVE_ROTEAMENTO = "pacote.atualizado";

    private final ObjectProvider<ConnectionFactory> connectionFactory;

    public ConfiguracaoRabbitMQ(ObjectProvider<ConnectionFactory> connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public Queue fila() {
        return new Queue(NOME_FILA, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(NOME_EXCHANGE);
    }

    @Bean
    public Binding ligacao(Queue fila, TopicExchange exchange){
        return BindingBuilder.bind(fila).to(exchange).with(CHAVE_ROTEAMENTO);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory.getObject());
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
