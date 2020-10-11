package com.nashtech.training.shape.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Getter
@Setter
public class ShapeCategoryDto {


    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String areaFormula;

    @NotNull
    private Set<String> attributes;

    public ShapeCategoryDto(Long id, String name, String areaFormula, Set<String> attributes) {
        this.id = id;
        this.name = name;
        this.areaFormula = areaFormula;
        this.attributes = attributes;
    }

}
