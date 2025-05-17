package com.cob.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends com.lib.token.cob.model.UserDto {
    @NotNull(message = "UserName should not be null")
    @NotEmpty(message = "UserName should not be empty")
    private String userName;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    private String role;

    private String adminSecretCode;
}
