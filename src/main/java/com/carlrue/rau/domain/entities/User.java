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

    private static final int MINIMUM_CHARACTER_LIMIT = 4;
    public boolean usernameIsValid() {
        return username != null && username.length() >= MINIMUM_CHARACTER_LIMIT;
    }
}
