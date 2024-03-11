package com.mayur.kafkaretry.model;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private Integer stepCount;
}
