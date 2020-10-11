package com.nashtech.training.rest.jpaTest;

import com.nashtech.training.shape.model.ShapeFormulaEntities;
import com.nashtech.training.shape.repository.ShapeFormulaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShapeEntityJPATest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ShapeFormulaRepository repository;

    @Test
    public void testFindNoShapeFormulaEntitiesIfRepositoryIsEmpty() {
        Iterable<ShapeFormulaEntities> shapeFormulaEntities = repository.findAll();

        assertThat(shapeFormulaEntities).isEmpty();
    }

    @Test
    public void testSaveShapeFormulaEntity() {
        ShapeFormulaEntities shapeFormulaEntities = repository.save(
                new ShapeFormulaEntities("SQUARE", "Square's AreaFormula", "a*a","Area"));

        assertThat(shapeFormulaEntities).hasFieldOrPropertyWithValue("name", "SQUARE");
        assertThat(shapeFormulaEntities).hasFieldOrPropertyWithValue("description", "Square's AreaFormula");
        assertThat(shapeFormulaEntities).hasFieldOrPropertyWithValue("formula", "a*a");
        assertThat(shapeFormulaEntities).hasFieldOrPropertyWithValue("category", "Area");
    }

        @Test
        public void testFindAllShapeFormulaEntities() {
            ShapeFormulaEntities entity1 =
                new ShapeFormulaEntities("SQUARE", "Square's AreaFormula", "a*a","Area");
        entityManager.persist(entity1);

        ShapeFormulaEntities entity2 =
                new ShapeFormulaEntities("RECTANGLE", "Rectangle's AreaFormula", "l*w","Area");
        entityManager.persist(entity2);

        ShapeFormulaEntities entity3 =
                new ShapeFormulaEntities("PARALLELOGRAM", "Parallelogram's AreaFormula", "a*h","Area");
        entityManager.persist(entity3);

        Iterable<ShapeFormulaEntities> shapeFormulaEntities = repository.findAll();

        assertThat(shapeFormulaEntities).hasSize(3).contains(entity1, entity2, entity3);
    }

    @Test
    public void testFindByIdShapeFormulaEntity() {
        ShapeFormulaEntities shapeFormula1 =
                new ShapeFormulaEntities("CIRCLE", "Circle's AreaFormula", "r*r*3.14","Area");
        entityManager.persist(shapeFormula1);

        ShapeFormulaEntities shapeFormula2 =
                new ShapeFormulaEntities("PARALLELOGRAM", "Parallelogram's AreaFormula", "a*h","Area");
        entityManager.persistAndGetId(shapeFormula2);

        ShapeFormulaEntities foundTutorial = repository.findById(shapeFormula1.getId()).get();

        assertThat(foundTutorial).isEqualTo(shapeFormula1);
    }

    @Test
    public void testFindShapeFormulaEntitiesByShapeName() {
        ShapeFormulaEntities entity1 = new ShapeFormulaEntities("SQUARE", "The areaFormula of Square", "a*a","Area");
        entityManager.persist(entity1);

        ShapeFormulaEntities entity2 = new ShapeFormulaEntities("CIRCLE", "The areaFormula of Circle", "r*r*3.14","Area");
        entityManager.persist(entity2);

        ShapeFormulaEntities entity3 = new ShapeFormulaEntities("SQUARE", "The CircumferenceFormula of Square", "a*4","Circumference");
        entityManager.persist(entity3);

        Iterable<ShapeFormulaEntities> shapeFormulaEntities = repository.findByNameContaining("SQUARE");

        assertThat(shapeFormulaEntities).hasSize(2).contains(entity1, entity3);
    }

    @Test
    public void testUpdateShapeFormulaEntitiesById() {
        ShapeFormulaEntities shapeFormula1 =
                new ShapeFormulaEntities("CIRCLE", "Circle's AreaFormula", "r*r*3.14","Area");
        entityManager.persist(shapeFormula1);

        ShapeFormulaEntities shapeFormula2 =
                new ShapeFormulaEntities("SQUARE", "The AreaFormula of Square", "a*a","Area");
        entityManager.persistAndGetId(shapeFormula2);

        ShapeFormulaEntities updatedShapeFormula2 = new ShapeFormulaEntities("SHAPE",
                "The AreaFormula of Square", "a*4", "CircumferenceFormula");

        ShapeFormulaEntities shapeEntity2 = repository.findById(shapeFormula2.getId()).get();
        shapeEntity2.setName(updatedShapeFormula2.getName());
        shapeEntity2.setDescription(updatedShapeFormula2.getDescription());
        shapeEntity2.setFormula(updatedShapeFormula2.getFormula());
        shapeEntity2.setCategory(updatedShapeFormula2.getCategory());
        repository.save(updatedShapeFormula2);

        ShapeFormulaEntities checkShapeFormula = repository.findById(shapeFormula2.getId()).get();
        assertThat(checkShapeFormula.getId()).isEqualTo(shapeFormula2.getId());
        assertThat(checkShapeFormula.getName()).isEqualTo(updatedShapeFormula2.getName());
        assertThat(checkShapeFormula.getDescription()).isEqualTo(updatedShapeFormula2.getDescription());
        assertThat(checkShapeFormula.getFormula()).isEqualTo(updatedShapeFormula2.getFormula());
        assertThat(checkShapeFormula.getCategory()).isEqualTo(updatedShapeFormula2.getCategory());
    }

    @Test
    public void testDeleteShapeFormulaEntitesById() {
        ShapeFormulaEntities shapeFormula1 = new ShapeFormulaEntities("CIRCLE",
                "Circle's AreaFormula", "r*r*3.14","Area");
        entityManager.persist(shapeFormula1);

        ShapeFormulaEntities shapeFormula2 = new ShapeFormulaEntities("SQUARE",
                "The AreaFormula of Square", "a*a","Area");
        entityManager.persist(shapeFormula2);

        ShapeFormulaEntities shapeFormula3 = new ShapeFormulaEntities("SHAPE",
                "The AreaFormula of Square", "a*4", "CircumferenceFormula");
        entityManager.persist(shapeFormula3);

        repository.deleteById(shapeFormula2.getId());

        Iterable<ShapeFormulaEntities> shapeFormulaEntities = repository.findAll();

        assertThat(shapeFormulaEntities).hasSize(2).contains(shapeFormula1, shapeFormula3);
    }

    @Test
    public void testDeleteAllShapeFormulaEntities() {
        entityManager.persist(new ShapeFormulaEntities("CIRCLE",
                "Circle's AreaFormula", "r*r*3.14","Area"));
        entityManager.persist(new ShapeFormulaEntities("SHAPE",
                "The AreaFormula of Square", "a*4", "CircumferenceFormula"));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}
