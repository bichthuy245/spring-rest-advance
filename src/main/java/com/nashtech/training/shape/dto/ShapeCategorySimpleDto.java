package com.nashtech.training.shape.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShapeCategorySimpleDto {
    private Long id;
    private String name;
    private String areaFormula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaFormula() {
        return areaFormula;
    }

    public void setAreaFormula(String areaFormula) {
        this.areaFormula = areaFormula;
    }
}
