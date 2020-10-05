package com.nashtech.training.shape.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
public class ShapeDto {

    Long id;

    @NotNull
    Long catId;

    String name;

    Map<String, Double> attributes = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Double> attributes) {
        this.attributes = attributes;
    }


}
