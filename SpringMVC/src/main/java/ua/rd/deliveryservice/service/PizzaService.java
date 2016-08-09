package ua.rd.deliveryservice.service;

import java.util.List;
import ua.rd.deliveryservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaService {

    Pizza find(Integer id);
    Pizza save(Pizza pizza);

    public List<Pizza> findAll();
}
