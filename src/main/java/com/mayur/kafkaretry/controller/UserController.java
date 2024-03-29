package com.mayur.kafkaretry.controller;

import com.mayur.kafkaretry.event.UserKafkaProducer;
import com.mayur.kafkaretry.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserKafkaProducer userKafkaProducer;

    @PostMapping("/users")
    public String saveUserInfo(@RequestBody User user) {
        userKafkaProducer.produceUserInfo(user);
        return "Success";
    }
}
