package com.nashtech.training.shape.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shape_formula")
@Getter
@Setter
public class ShapeFormulaEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "formula")
    private String formula;

    @Column(name = "category")
    private String category;

    public ShapeFormulaEntities() {
    }

    public ShapeFormulaEntities(String name, String description, String formula, String category) {
        this.name = name;
        this.description = description;
        this.formula = formula;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ShapeFormulaEntities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", formula='" + formula + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
