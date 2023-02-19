package com.yorra.twinkle.model.dtos;

import com.yorra.twinkle.model.entities.Category;
import com.yorra.twinkle.model.entities.Characteristic;
import com.yorra.twinkle.model.entities.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Size(max = 255)
    @NotNull
    private String name;

    private String code;

    @Size(max = 5000)
    private String description;

    private boolean available;

    private Date createdAt;

    private Date updatedAt;

    @NotNull
    private double price;

    @NotNull
    private double finalPrice;

    private int height;

    private int weight;

    private Set<Characteristic> characteristicSet;

    private Category category;

    private Set<File> files;

    private File thumbnail;
}
