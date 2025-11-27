package org.example.users.controller.dto.UserDto;

import jakarta.validation.constraints.*;

public record UserToSave (
        @Email
        @NotNull
        String email,

        @NotBlank
        @Size(min = 6)
        String password
){
}
