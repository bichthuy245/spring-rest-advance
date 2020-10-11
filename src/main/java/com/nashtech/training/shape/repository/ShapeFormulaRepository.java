package com.nashtech.training.shape.repository;

import com.nashtech.training.shape.model.ShapeFormulaEntities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShapeFormulaRepository extends JpaRepository<ShapeFormulaEntities, Long> {

    List<ShapeFormulaEntities> findByNameContaining(String name);
}
