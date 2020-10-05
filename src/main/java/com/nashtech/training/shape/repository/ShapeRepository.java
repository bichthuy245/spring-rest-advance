package com.nashtech.training.shape.repository;

import com.nashtech.training.shape.model.Shape;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {

    @EntityGraph(attributePaths = "attributes")
    List<Shape> findAllWithAttributeByUser(String user);

    @EntityGraph(attributePaths = "attributes")
    Optional<Shape> findOneWithAttributeById(Long id);
}
