package repository;

import Entity.Pizza;
import Entity.PizzaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 30.03.2016.
 */
public class InMemPizzaRepository implements PizzaRepository {
    private List<Pizza> pizzas = new ArrayList<>();

    {
        pizzas.add(new Pizza(1, "Pizza1", 12.4, PizzaType.Meat));
        pizzas.add(new Pizza(2, "Pizza2", 24.4, PizzaType.Vegeterian));
        pizzas.add(new Pizza(3, "Pizza3", 22.5, PizzaType.Sea));
    }

    public Pizza getPizzaByID(Integer id) {
        int index = id - 1;
        return pizzas.get(index);
    }
}
