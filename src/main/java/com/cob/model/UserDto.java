package com.cob.model;

import com.fasterxml.jackson.annotation.JsonView;
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
    @NotEmpty(message = "UserName must not be empty")
    @JsonView({Views.Request.class, Views.Response.class})
    private String userName;

    @NotEmpty(message = "Password must not be empty")
    @JsonView(Views.Request.class)
    private String password;

    @JsonView(Views.Response.class)
    private String role;

    @JsonView(Views.Request.class)
    private String adminSecretCode;
}
