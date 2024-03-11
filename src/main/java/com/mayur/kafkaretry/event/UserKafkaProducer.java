package com.mayur.kafkaretry.event;

import com.mayur.kafkaretry.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserKafkaProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void produceUserInfo(User user) {
        SendResult<String, User> result = kafkaTemplate.send("user.info", user.getFirstName(), user).join();
        log.info("Produced User object on Partition: {}, Offset: {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
    }
}
