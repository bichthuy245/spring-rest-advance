package com.nashtech.training.shape.utils;

import com.nashtech.training.shape.CalcAreaException;
import lombok.extern.slf4j.Slf4j;
import org.cheffo.jeplite.JEP;
import org.cheffo.jeplite.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Slf4j
public class ShapeCalculatorUtils {

    private static Logger log = LoggerFactory.getLogger(ShapeCalculatorUtils.class);

    public static double calcArea(String formula, Map<String, Double> values) throws CalcAreaException {
        JEP jep = new JEP();
        jep.addStandardFunctions();
        for (Map.Entry<String, Double> me : values.entrySet()) {
            jep.addVariable(me.getKey(), Double.valueOf(me.getValue()));
        }
        try {
            jep.parseExpression(formula);
            return jep.getValue();
        } catch (ParseException ex) {
            log.error("calc Area:", ex);
            throw new CalcAreaException(ex.getMessage());
        }

    }
}
