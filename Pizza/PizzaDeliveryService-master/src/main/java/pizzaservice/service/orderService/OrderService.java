package pizzaservice.service.orderService;


import pizzaservice.domain.customer.Customer;
import pizzaservice.domain.order.Order;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID);

}