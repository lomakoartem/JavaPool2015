package ua.rd.pizzaservice.domain.discount;

import ua.rd.pizzaservice.domain.Pizza;

import java.util.List;

/**
 *
 * @author andrii
 */
public class PizzaDiscount implements Discountable {

    public PizzaDiscount(List<Pizza> pizzas) {
    }

    @Override
    public double calculateDiscount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
