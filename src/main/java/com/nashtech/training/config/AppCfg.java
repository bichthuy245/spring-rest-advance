package com.nashtech.training.config;

import com.nashtech.training.shape.model.ShapeAttribute;
import com.nashtech.training.shape.model.ShapeCategory;
import com.nashtech.training.shape.service.ShapeService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCfg implements ApplicationContextAware, InitializingBean {

    @Autowired
    private ShapeService shapeService;

    public AppCfg(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        // triangle
        ShapeCategory shapeCategory = new ShapeCategory();
        shapeCategory.setName("TRIANGLE");
        shapeCategory.setAreaFormula("(sqrt( (a+b+c) * (a+b-c) * (b+c-a) * (c+a-b)))/4");
        shapeCategory.getAttributes().add(new ShapeAttribute("a", shapeCategory));
        shapeCategory.getAttributes().add(new ShapeAttribute("c", shapeCategory));
        shapeCategory.getAttributes().add(new ShapeAttribute("b", shapeCategory));
        this.shapeService.saveCategory(shapeCategory);

        //square
        shapeCategory = new ShapeCategory();
        shapeCategory.setName("SQUARE");
        shapeCategory.setAreaFormula("a*a");
        shapeCategory.getAttributes().add(new ShapeAttribute("a", shapeCategory));
        this.shapeService.saveCategory(shapeCategory);

        //rectangle
        shapeCategory = new ShapeCategory();
        shapeCategory.setName("RECTANGLE");
        shapeCategory.setAreaFormula("l*w");
        shapeCategory.getAttributes().add(new ShapeAttribute("l", shapeCategory));
        shapeCategory.getAttributes().add(new ShapeAttribute("w", shapeCategory));
        this.shapeService.saveCategory(shapeCategory);

        //parallelogram
        shapeCategory = new ShapeCategory();
        shapeCategory.setName("PARALLELOGRAM");
        shapeCategory.setAreaFormula("a*h");
        shapeCategory.getAttributes().add(new ShapeAttribute("a", shapeCategory));
        shapeCategory.getAttributes().add(new ShapeAttribute("h", shapeCategory));
        this.shapeService.saveCategory(shapeCategory);

        //circle
        shapeCategory = new ShapeCategory();
        shapeCategory.setName("CIRCLE");
        shapeCategory.setAreaFormula("r*r*3.14");
        shapeCategory.getAttributes().add(new ShapeAttribute("r", shapeCategory));
        this.shapeService.saveCategory(shapeCategory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
}
