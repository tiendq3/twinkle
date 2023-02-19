package com.yorra.twinkle.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(max = 500)
    @NotNull
    private String name;

    @Column(name = "sku")
    @Size(max = 255)
    private String sku;

    @Column(name = "description")
    @Size(max = 500)
    private String description;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "update_at")
    private Instant updatedAt;

    @Column(name = "price")
    @NotNull
    private Double price;

    @Column(name = "final_price")
    @NotNull
    private Double finalPrice;

    @Column(name = "rate")
    private Double rating;

    @Column(name = "model_height")
    private Double modelHeight;

    @Column(name = "model_weight")
    private Double modelWeight;

    @ManyToMany
    private Set<Characteristic> characteristicSet;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    private Set<File> files;

    @ManyToOne
    @JoinColumn(name = "thumbnail")
    private File thumbnail;
}
