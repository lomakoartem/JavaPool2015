import Entity.Customer;
import Entity.Order;
import Entity.Pizza;
import repository.InMemOrderRepository;
import repository.InMemPizzaRepository;
import repository.OrderRepository;
import repository.PizzaRepository;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 30.03.2016.
 */
public class SimpleOrderService implements OrderService {


    private OrderRepository orderRepository = new InMemOrderRepository();
    private PizzaRepository pizzaRepository = new InMemPizzaRepository();

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
        Order newOrder = createOrder(customer, pizzas);

        orderRepository.saveOrder(newOrder);  // set Entity.Order Id and save Entity.Order to in-memory list
        return newOrder;
    }

    private Order createOrder(Customer customer, List<Pizza> pizzas) {
        return new Order(customer, pizzas);
    }

    private List<Pizza> pizzasByArrOfId(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Entity.Pizza from predifined in-memory list
        }
        return pizzas;
    }


}