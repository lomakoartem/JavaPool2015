package ua.rd.deliveryservice.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.service.PizzaService;

/**
 *
 * @author andrii
 */
public class PizzaConverter implements Converter<String, Pizza> {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public Pizza convert(String pizzaId) {
        Pizza pizza;        
        if ((pizzaId == null) || pizzaId.isEmpty()) {
            pizza = new Pizza();
        } else {
            int id = Integer.valueOf(pizzaId);
            if (id <= 0) {
                throw new IllegalArgumentException();
            }
            pizza = pizzaService.find(id);
        }
        return pizza;
    }
}
