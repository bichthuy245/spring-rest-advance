package com.nashtech.training.shape.service;


import com.nashtech.training.shape.model.Shape;
import com.nashtech.training.shape.model.ShapeCategory;

import java.util.List;
import java.util.Optional;

public interface ShapeService {

    List<ShapeCategory> getAllCategory();

    List<ShapeCategory> getAllCategoryFull();

    ShapeCategory saveCategory(ShapeCategory category);

    List<Shape> getAllShape();

    List<Shape> getAllShape(String user);

    Optional<Shape> getShapeById(long id) throws InterruptedException;

    Optional<ShapeCategory> getCategory(long id);

    Shape save(Shape shape);

    boolean deleteShape(long id);

    boolean deleteCategory(long id);

}
