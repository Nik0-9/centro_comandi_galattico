package it.galactic.command.centrallogic.config;

import it.galactic.command.centrallogic.dto.GalaxyMissionDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configurazione per il consumer Kafka di CentralLogic.
 * Specifica come connettersi a Kafka e come deserializzare i messaggi JSON.
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // --- Questo bean è quello corretto ---
    @Bean
    public ConsumerFactory<String, GalaxyMissionDTO> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 1. Creiamo un JsonDeserializer che sa già che deve produrre oggetti GalaxyMissionDTO.
        JsonDeserializer<GalaxyMissionDTO> deserializer = new JsonDeserializer<>(GalaxyMissionDTO.class);

        // 2. Gli diciamo di NON usare gli header di tipo, perché abbiamo un tipo di default.
        deserializer.setUseTypeHeaders(false);
        // 3. Gli diciamo di fidarsi di tutti i package (buona pratica per evitare problemi).
        deserializer.addTrustedPackages("*");

        // 4. Passiamo il deserializer specifico alla factory.
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    // --- E questo bean usa la factory corretta ---
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GalaxyMissionDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GalaxyMissionDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}