package com.cob.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull(message = "UserName should not be null")
    @NotEmpty(message = "UserName should not be empty")
    private String userName;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
