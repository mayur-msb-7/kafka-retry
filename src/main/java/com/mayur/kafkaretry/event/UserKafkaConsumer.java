package com.mayur.kafkaretry.event;

import com.mayur.kafkaretry.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.SameIntervalTopicReuseStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserKafkaConsumer {
    @KafkaListener(topics = "user.info")
    @RetryableTopic(retryTopicSuffix = ".retry", dltTopicSuffix = ".dlt", backoff = @Backoff(delay = 10000), sameIntervalTopicReuseStrategy = SameIntervalTopicReuseStrategy.SINGLE_TOPIC, autoStartDltHandler = "false")
    public void processUserInfo(@Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic, @Payload User user, Acknowledgment ack) {
        log.info("Received event from Kafka topic: {}, event: {}", receivedTopic, user);
        // Perform step 1 action
        user.setStepCount(1);
        // Perform step 2 action
        user.setStepCount(2);
        // While performing step 3 action error occurred
        throw new RuntimeException("Exception occurred while processing event");
    }
}
