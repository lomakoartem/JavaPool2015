package ua.rd.pizzaservice.service;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.discount.Discountable;

import java.util.List;

/**
 *
 * @author andrii
 */
public interface DiscountsProvider {

    public List<Discountable> getDiscounts(Order order);
    
}
