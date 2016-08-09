package ua.rd.deliveryservice.repository;

import java.util.List;
import ua.rd.deliveryservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {

    Pizza find(Integer id);

    Pizza save(Pizza pizza);

    public List<Pizza> findAll();
}
