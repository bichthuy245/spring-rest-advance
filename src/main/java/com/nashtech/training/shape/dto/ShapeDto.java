package com.nashtech.training.shape.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
public class ShapeDto {

    Long id;

    @NotNull
    Long catId;

    String name;

    Map<String, Double> attributes = new HashMap<>();

}
