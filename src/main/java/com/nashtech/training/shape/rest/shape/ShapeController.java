package com.nashtech.training.shape.rest.shape;

import com.nashtech.training.shape.CalcAreaException;
import com.nashtech.training.shape.model.*;
import com.nashtech.training.shape.service.ShapeService;
import com.nashtech.training.shape.utils.ShapeCalculatorUtils;
import com.nashtech.training.shape.utils.ShapeMapper;
import com.nashtech.training.shape.dto.ShapeCategoryDto;
import com.nashtech.training.shape.dto.ShapeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shape")
public class ShapeController {

    private static final Logger log = LoggerFactory.getLogger(ShapeController.class);

    final private ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Data Found",content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized!",content = @Content),
            @ApiResponse(responseCode = "403", description = "forbidden!!!",content = @Content)})
    @GetMapping("/categories")
    ResponseEntity<List<ShapeCategoryDto>> getAllCategory(){
        List<ShapeCategory> categories =  this.shapeService.getAllCategoryFull();
        return ResponseEntity.ok(categories.stream().map(ShapeMapper.INSTANCE::toDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Get Shape Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ShapeCategoryDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id of ShapeCategory",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "ShapeCategory of Shape not found",
                    content = @Content)})
    @GetMapping("/category/{catid}")
    ResponseEntity<ShapeCategoryDto> getCategory(@PathVariable("catid") long id){
        Optional<ShapeCategory>  category = this.shapeService.getCategory(id);
        if (category.isPresent()) {
            return ResponseEntity.ok().eTag(String.valueOf(id)).body(ShapeMapper.INSTANCE.toDto(category.get()));
        } else return ResponseEntity.notFound().eTag(String.valueOf(id)).build();
//        return category.map(e -> ResponseEntity.ok(ShapeMapper.INSTANCE.toDto(e))).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Shape Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/category/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable("id") long id){
        if(this.shapeService.deleteCategory(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Save new Shape Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "412", description = "Precondition Failed", content = @Content)})
    @PostMapping("/category/save")
    ResponseEntity<Long> saveCategory(@Valid @RequestBody ShapeCategoryDto shapeCategoryDto){
        final ShapeCategory category = ShapeMapper.INSTANCE.toEntity(shapeCategoryDto);
        try{
            final ShapeCategory categoryDb = this.shapeService.saveCategory(category);
            return ResponseEntity.ok(categoryDb.getId());
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Calculate formula of Shape Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "412", description = "Precondition Failed", content = @Content)})
    @PostMapping("/calc")
    ResponseEntity<Double> calcAreaShape(@Valid @RequestBody ShapeDto shapeDto){
        Optional<ShapeCategory> category = this.shapeService.getCategory(shapeDto.getCatId());

        if(category.isPresent() && validateAttribute(category.get(), shapeDto)){
            Map<String, Double> mapAttribute = new HashMap<>();
            shapeDto.getAttributes().entrySet().forEach(e -> mapAttribute.put(e.getKey(), e.getValue()));
            try {
                return ResponseEntity.ok(ShapeCalculatorUtils.calcArea(category.get().getAreaFormula(),mapAttribute));
            } catch (CalcAreaException e) {
                log.error("calcAreaShape", e);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Save new Shape")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content),
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "412", description = "Precondition Failed", content = @Content)})
    @PutMapping("/save")
    ResponseEntity<Long> saveShape(@Valid @RequestBody ShapeDto shapeDto){
       throw new NotImplementedException();
    }

    @Operation(summary = "Get all data of Shape")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Data Found",content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized!",content = @Content),
            @ApiResponse(responseCode = "403", description = "forbidden!!!",content = @Content)})
    @GetMapping("/all")
    ResponseEntity<List<ShapeDto>> getAllShape(){
        List<Shape> shapes =  this.shapeService.getAllShape();
        return ResponseEntity.ok(shapes.stream().map(ShapeMapper.INSTANCE::toDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Get Data of Shape by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShapeDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid id of Shape",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Data Shape not found",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<ShapeDto> getShape(@PathVariable("id") long id) {
        Optional<Shape> shape = null;
        try {
            shape = this.shapeService.getShapeById(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shape.map(e -> ResponseEntity.ok(ShapeMapper.INSTANCE.toDto(e))).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete data of Shape by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteShape(@PathVariable("id") long id){
        if(this.shapeService.deleteShape(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    private boolean validateAttribute(ShapeCategory category, ShapeDto shapeDto){
        if(category.getAttributes().size() != shapeDto.getAttributes().size()){
            return false;
        }
        Set<String> fname1 = category.getAttributes().stream().map(ShapeAttribute::getAttributeName).collect(Collectors.toSet());
        Set<String> fname2 = shapeDto.getAttributes().keySet();
        return fname1.containsAll(fname2);
    }

}
