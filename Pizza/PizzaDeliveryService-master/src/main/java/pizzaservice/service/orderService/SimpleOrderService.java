package pizzaservice.service.orderService;

import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;
import pizzaservice.repository.order.OrderRepository;
import pizzaservice.repository.pizza.PizzaRepository;

import java.util.ArrayList;
import java.util.List;


public class SimpleOrderService implements OrderService {

   // private ServiceLocator serviceLocator = ServiceLocator.getInstance();

    private OrderRepository orderRepository;// = (OrderRepository) serviceLocator.lookup("orderRepository");
    private PizzaRepository pizzaRepository;// = (PizzaRepository) serviceLocator.lookup("pizzaRepository");

    public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
        Order newOrder = createOrder(customer, pizzas);

        orderRepository.saveOrder(newOrder); // set Order Id and save Order to
                                             // in-memory list
        return newOrder;
    }

    private Order createOrder(Customer customer, List<Pizza> pizzas) {
        Order newOrder = new Order(customer, pizzas);
        return newOrder;
    }

    private List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(pizzaRepository.getPizzaByID(id)); // get Pizza from
                                                          // predifined
                                                          // in-memory
                                                          // list
        }
        return pizzas;
    }

}
