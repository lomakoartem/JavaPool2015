import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.service.SimpleOrderService;

@ContextConfiguration(locations = {
        "classpath:/appContext.xml"}, inheritInitializers = true)
public class SimpleOrderServiceTest extends NewRepositoryTestConfig {
    @Autowired
    SimpleOrderService orderService;

    @Test
    public final void testPlaceNewOrder() {
        System.out.println("placeNewOrder");
        Customer customer = null;
        Integer[] pizzasId = new Integer[]{1};
        Order expResult = null;
        Order result = orderService.placeNewOrder(customer, pizzasId);
// assertEquals(expResult, result);
        System.out.println(result);
    }
}