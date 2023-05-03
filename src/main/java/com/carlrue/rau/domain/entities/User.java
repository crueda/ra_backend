package com.carlrue.rau.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;
    private String username;
    private String name;
    private String email;

    private static final int MIN_CHARACTER_LIMIT = 4;
    private static final int MAX_CHARACTER_LIMIT = 21;
    public boolean usernameIsValid() {
        return username != null && username.length() >= MIN_CHARACTER_LIMIT && username.length() < MAX_CHARACTER_LIMIT;
    }
}
