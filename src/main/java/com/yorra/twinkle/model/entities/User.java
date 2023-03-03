package com.yorra.twinkle.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorra.twinkle.config.Constants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(max = 10)
    private String phone;

    @NotNull
    @JsonIgnore
    private String password;

    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String address;

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();
}
