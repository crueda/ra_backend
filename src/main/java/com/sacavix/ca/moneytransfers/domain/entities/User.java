package com.sacavix.ca.moneytransfers.domain.entities;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String name;
    private String email;

}
