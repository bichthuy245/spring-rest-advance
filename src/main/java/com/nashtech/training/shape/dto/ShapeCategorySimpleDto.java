package com.nashtech.training.shape.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShapeCategorySimpleDto {
    private Long id;
    private String name;
    private String areaFormula;
}
