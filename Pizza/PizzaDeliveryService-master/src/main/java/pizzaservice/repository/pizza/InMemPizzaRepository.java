package pizzaservice.repository.pizza;

import org.springframework.stereotype.Repository;
import pizzaservice.domain.pizza.Pizza;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

    List<Pizza> pizzas = new ArrayList<>();

    @PostConstruct
    public void cookPizzas() {
        pizzas.add(new Pizza(1, "Margarita", 60d, Pizza.Type.MEAT));
        pizzas.add(new Pizza(2, "SeaPizza", 90d, Pizza.Type.SEA));
        pizzas.add(new Pizza(3, "Ayurveda", 80d, Pizza.Type.VEGETERIAN));
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        Pizza result = null;
        for (Pizza pizza : pizzas) {
            if (pizza.getId().equals(id)) {
                result = pizza;
                break;
            }
        }
        return result;
    }

}
