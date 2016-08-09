package ua.rd.deliveryservice;

import ua.rd.deliveryservice.domain.Order;
import ua.rd.deliveryservice.domain.Customer;
import ua.rd.deliveryservice.infrastructure.ApplicationContext;
import ua.rd.deliveryservice.infrastructure.JavaConfig;
import ua.rd.deliveryservice.infrastructure.JavaConfigApplicationContext;
import ua.rd.deliveryservice.repository.PizzaRepository;
import ua.rd.deliveryservice.service.OrderService;

public class DeliveryApp {

    public static void main(String[] args) throws Exception {
        Customer customer = null;

//        PizzaRepository pizzaRepository = new InMemPizzaRepository();
//        OrderRepository orderRepository = new InMemOrderRepository();
//        PizzaService pizzaService = new SimplePizzaService(pizzaRepository);        
//        
//        SimpleOrderService orderService = new SimpleOrderService(
//                orderRepository, 
//                pizzaService);
//        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
//
//        System.out.println(order);
        
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1));
//        System.out.println(pizzaRepository.find(2));
        
        OrderService orderService = (OrderService) context.getBean("orderService");
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);
    }

}
