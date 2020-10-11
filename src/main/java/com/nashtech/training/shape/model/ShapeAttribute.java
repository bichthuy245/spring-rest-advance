package com.nashtech.training.shape.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(name = "SHAPE_ATTRIBUTES")
@Data
@IdClass(ShapeAttributeKey.class)
public class ShapeAttribute {

    @Id
    @Column(name = "ATTRIBUTE_NAME")
    @NotNull
    @Size(min = 1, max = 5)
    private String attributeName;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private ShapeCategory category;

    public ShapeAttribute() {
    }

    public ShapeAttribute(@NotNull @Size(min = 1, max = 5) String attributeName, ShapeCategory category) {
        this.attributeName = attributeName;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShapeAttribute that = (ShapeAttribute) o;
        return Objects.equals(attributeName, that.attributeName) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, category);
    }
}
