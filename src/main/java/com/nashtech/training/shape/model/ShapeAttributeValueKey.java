package com.nashtech.training.shape.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShapeAttributeValueKey implements Serializable {

    private String attributeName;

    private long shape;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public long getShape() {
        return shape;
    }

    public void setShape(long shape) {
        this.shape = shape;
    }
}
