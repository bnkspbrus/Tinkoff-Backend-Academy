package com.tinkoffacademy.rancher.config;

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
}
