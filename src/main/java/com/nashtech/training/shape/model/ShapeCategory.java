package com.nashtech.training.shape.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity(name = "SHAPE_CATEGORY")
@Data
@ToString(exclude = {"attributes"})
public class ShapeCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME", length = 50, unique = true)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
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

    public Set<ShapeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<ShapeAttribute> attributes) {
        this.attributes = attributes;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "category_id")
    private Set<ShapeAttribute> attributes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShapeCategory that = (ShapeCategory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
