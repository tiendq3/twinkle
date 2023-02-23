package com.yorra.twinkle.model.dtos;

import com.yorra.twinkle.model.entities.Category;
import com.yorra.twinkle.model.entities.Characteristic;
import com.yorra.twinkle.model.entities.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @Size(max = 255)
    @NotNull
    private String name;

    @Size(max = 255)
    private String sku;

    @Size(max = 500)
    private String description;

    private Boolean isAvailable;

    private Instant createdAt;

    private Instant updatedAt;

    @NotNull
    private Double price;

    @NotNull
    private Double finalPrice;

    private Double rating;

    private Double modelHeight;

    private Double modelWeight;

    private Set<Characteristic> characteristics;

    private Category category;

    private Set<File> files;

    private File thumbnail;
}
