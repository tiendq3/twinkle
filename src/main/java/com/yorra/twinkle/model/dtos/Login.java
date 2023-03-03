package com.yorra.twinkle.model.dtos;

import com.yorra.twinkle.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    private String phone;

    @NotNull
    private String password;
}
