package com.yorra.twinkle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorra.twinkle.model.enums.EFileType;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    // UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 500, nullable = false)
    @Size(max = 500)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private EFileType fileType;

    @Column(name = "ext")
    @NotNull
    private String ext;

    @Column(name = "path")
    @NotNull
    @JsonIgnore
    private String path;

    @Column(name = "size")
    @NotNull
    private long size;
}
