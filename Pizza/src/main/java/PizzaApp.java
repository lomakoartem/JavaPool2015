import Entity.Customer;
import Entity.Order;

/**
 * Created by lomak on 30.03.2016.
 */
public class PizzaApp {

    public static void main(String[] args) {
        Customer customer = new Customer(1,"Artem");
        Order order;
        OrderService orderService = new SimpleOrderService();
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);
    }

}