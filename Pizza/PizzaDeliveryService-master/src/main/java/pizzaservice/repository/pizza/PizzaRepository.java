package pizzaservice.repository.pizza;


import pizzaservice.domain.pizza.Pizza;

public interface PizzaRepository {

	Pizza getPizzaByID(Integer id);
}
