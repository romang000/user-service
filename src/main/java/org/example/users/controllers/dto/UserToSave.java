package org.example.users.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserToSave (
        @Email
        @NotNull
        String email,

        @NotNull
        String password
){
}
