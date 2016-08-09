package ua.rd.deliveryservice.service;

import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.domain.Order;
import ua.rd.deliveryservice.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.deliveryservice.repository.OrderRepository;

/**
 *
 * @author andrii
 */
@Service
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    @Autowired
    public SimpleOrderService(
            OrderRepository orderRepository,
            PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Override
//    @Benchmark
    public Order placeNewOrder(Customer customer, int... pizzaID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzaID) {
            pizzas.add(findPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }
    
    @Lookup(value = "order")
    protected Order createNewOrder() {
        return null;
    } 

    private Pizza findPizzaByID(Integer id) {
        return pizzaService.find(id);
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
}
