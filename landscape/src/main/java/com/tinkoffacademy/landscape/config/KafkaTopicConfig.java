package com.tinkoffacademy.landscape.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic landscapeRancher() {
        return TopicBuilder
                .name("landscape-rancher")
                .build();
    }

    @Bean
    public NewTopic rancherLandscape() {
        return TopicBuilder
                .name("rancher-landscape")
                .build();
    }

    @Bean
    public NewTopic landscapeHandyman() {
        return TopicBuilder
                .name("landscape-handyman")
                .build();
    }

    @Bean
    public NewTopic handymanLandscape() {
        return TopicBuilder
                .name("handyman-landscape")
                .build();
    }
}
