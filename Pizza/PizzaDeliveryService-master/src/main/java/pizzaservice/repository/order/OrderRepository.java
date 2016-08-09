package pizzaservice.repository.order;


import pizzaservice.domain.order.Order;

public interface OrderRepository {

	Long saveOrder(Order newOrder);
}
