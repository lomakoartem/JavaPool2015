package repository;

import Entity.Pizza;

/**
 * Created by lomak on 30.03.2016.
 */
public interface PizzaRepository {
    Pizza getPizzaByID(Integer id);
}
