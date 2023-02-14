package com.yorra.twinkle.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(max = 255)
    @NotNull
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;


    private boolean available;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "price")
    @NotNull
    private double price;

    private double rate;

}
