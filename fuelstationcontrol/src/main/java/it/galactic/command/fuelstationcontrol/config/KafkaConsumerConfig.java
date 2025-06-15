package it.galactic.command.fuelstationcontrol.config;

import it.galactic.command.fuelstationcontrol.dto.GalaxyMissionDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, GalaxyMissionDTO> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        
        // Configura il deserializzatore per il nostro DTO
        JsonDeserializer<GalaxyMissionDTO> deserializer = new JsonDeserializer<>(GalaxyMissionDTO.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*"); // Per semplicità, accettiamo tutti i package
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(), // Deserializzatore per la chiave
            deserializer            // Deserializzatore per il valore (il nostro DTO)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GalaxyMissionDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GalaxyMissionDTO> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
