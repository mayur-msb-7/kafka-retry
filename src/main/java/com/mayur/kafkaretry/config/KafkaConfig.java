package com.mayur.kafkaretry.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableScheduling
public class KafkaConfig extends RetryTopicConfigurationSupport {
    @Bean
    public NewTopic createMainTopic() {
        return TopicBuilder
                .name("user.info")
                .partitions(3)
                .build();
    }

    @Override
    protected void configureBlockingRetries(BlockingRetriesConfigurer blockingRetries) {
        blockingRetries
                .retryOn(RuntimeException.class)
                .backOff(new FixedBackOff(3000, 5));
    }
}

