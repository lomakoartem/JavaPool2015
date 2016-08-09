package pizzaservice.repository.order;

import org.springframework.stereotype.Repository;
import pizzaservice.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    @Override
    public Long saveOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder.getId();
    }

}
