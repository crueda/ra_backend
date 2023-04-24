package com.sacavix.ca.moneytransfers.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserCommand {
    private String username;
    private String name;
    private String email;
}
