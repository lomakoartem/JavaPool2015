package ua.rd.deliveryservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.deliveryservice.domain.Customer;
import ua.rd.deliveryservice.domain.Order;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.repository.PizzaRepository;
import ua.rd.deliveryservice.service.OrderService;

/**
 *
 * @author andrii
 */
public class SpringDeliveryApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext(
                        "repositoryContext.xml"
                );

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"},
                        repositoryContext);

        appContext.publishEvent(new ApplicationEvent(appContext) {
        });
        
        ApplicationContext parent = appContext.getParent();
        System.out.println(parent);

        for (String name : appContext.getBeanDefinitionNames())
            System.out.println(name);
        
//        Pizza pizza = (Pizza) appContext.getBean("pizzaFactoryBean");
//        System.out.println(pizza);
        
        Customer c = appContext.getBean("customer", Customer.class);
        System.out.println(c);

        Customer nc = appContext.getBean("newCustomer", Customer.class);
        System.out.println(nc);
        System.out.println(appContext.getBean("newCustomer", Customer.class));

        PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepo");
        System.out.println(pizzaRepository.find(1));

        OrderService orderService = (OrderService) appContext.getBean("orderService");
        Order order = orderService.placeNewOrder(null, 1);
        System.out.println(order);

        repositoryContext.close();
        appContext.close();
    }
}
