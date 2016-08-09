import Entity.Customer;
import Entity.Order;

/**
 * Created by lomak on 30.03.2016.
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Integer... pizzasID);
}
