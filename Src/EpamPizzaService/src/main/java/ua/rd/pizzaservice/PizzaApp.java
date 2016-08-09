package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.service.OrderService;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.infrustructure.ApplicationContext;
import ua.rd.pizzaservice.infrustructure.JavaConfigApplicationContext;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author andrii
 */
public class PizzaApp {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        Pizza pizza = new Pizza();

        pizza.setName("SisikiPiski");
        pizza.setPrice(120.3);
        pizza.setType(Pizza.PizzaType.MEAT);
        Address newAddress = new Address();
        newAddress.setId(1);
        newAddress.setCity("Kiev");
        Customer customer = new Customer();

        customer.setName("Andrii");
      //  customer.setAddress(Arrays.asList(newAddress,newAddress));
        customer.setPhones(Arrays.asList("063 978 24 16"));
        try {
            em.getTransaction().begin();

            em.persist(newAddress);
            em.persist(customer);
            em.persist(pizza);
            em.getTransaction().commit();


            //  Pizza p =em.find(Pizza.class,2);
            //   System.out.println(p);
        } finally {
            em.close();
            emf.close();
        }
        /*Customer customer = null;
        Order order;
        
        ApplicationContext ac = new JavaConfigApplicationContext();
        PizzaRepository pizzaRepository = (PizzaRepository) ac.getBean("pizzaRepository");
        System.out.println(pizzaRepository.getPizzaByID(1));
        
        OrderService orderService = (OrderService)
                ac.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        System.out.println(order);*/
    }
}
 