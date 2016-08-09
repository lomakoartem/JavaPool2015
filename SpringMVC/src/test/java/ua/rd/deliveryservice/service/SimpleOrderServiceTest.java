package ua.rd.deliveryservice.service;



import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.deliveryservice.domain.Customer;
import ua.rd.deliveryservice.domain.Order;

/**
 *
 * @author andrii
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/appContext.xml", 
    "classpath:/repositoryTestContext.xml"})
public class SimpleOrderServiceTest {
    
//    private final ConfigurableApplicationContext appContext;
    
    @Autowired
    private OrderService orderService;

//    public SimpleOrderServiceTest() {
//         ConfigurableApplicationContext repositoryContext
//                = new ClassPathXmlApplicationContext(
//                        "repositoryTestContext.xml"
//                );
//
//        appContext = new ClassPathXmlApplicationContext(
//                        new String[]{"appContext.xml"},
//                        repositoryContext);
//    }
    

    @Test
    public void testPlaceNewOrder() {
        System.out.println("placeNewOrder");
        Customer customer = null;
        int[] pizzaID = {1};
     //   OrderService instance = appContext.getBean(OrderService.class);
        
        Order result = orderService.placeNewOrder(customer, pizzaID);
        System.out.println(result);
        assertNotNull(result);
        
    }

    
    
}
