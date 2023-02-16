package com.yorra.twinkle.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yorra.twinkle.model.enums.EColor;
import com.yorra.twinkle.model.enums.ESize;
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

    @Column(name = "size")
    @NotNull
    private ESize sizeEnum;

    @Column(name = "color")
    @NotNull
    private EColor colorEnum;

    @Column(name = "amount")
    @NotNull
    private int amount;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
