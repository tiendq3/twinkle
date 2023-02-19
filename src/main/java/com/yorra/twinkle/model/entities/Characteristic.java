package com.yorra.twinkle.model.entities;

import com.yorra.twinkle.model.enums.ECharacteristicType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "characteristics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Characteristic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "characteristic_type")
    @NotNull
    private ECharacteristicType characteristicType;

    @Column(name = "value")
    @NotNull
    private String value;

    @Column(name = "qualifier")
    private String qualifier;
}
