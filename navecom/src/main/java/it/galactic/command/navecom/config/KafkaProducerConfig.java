package it.galactic.command.navecom.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import it.galactic.command.navecom.dto.GalaxyMissionDTO;

import java.util.HashMap;
import java.util.Map;
/*
 * Di default, Spring Kafka invia semplici stringhe. Dobbiamo dirgli 
 * di serializzare il nostro oggetto `GalaxyMissionDTO` in formato JSON.
 */
@Configuration
public class KafkaProducerConfig {
     // L'URL del server Kafka viene preso dal file application.properties
     @Value("${spring.kafka.bootstrap-servers}")
     private String bootstrapServers;
 
     @Bean
     public ProducerFactory<String, GalaxyMissionDTO> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        // MODIFICATO: La factory ora è tipizzata correttamente
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    // MODIFICATO: Il KafkaTemplate ora ha il tipo esatto richiesto dal service
    public KafkaTemplate<String, GalaxyMissionDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
