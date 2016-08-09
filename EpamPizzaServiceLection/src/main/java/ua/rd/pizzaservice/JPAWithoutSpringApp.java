package ua.rd.pizzaservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.RegistratedCustomer;
import ua.rd.pizzaservice.domain.State;

/**
 *
 * @author andrii
 */
public class JPAWithoutSpringApp {

    public static void main(String[] args) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        Pizza pizza = new Pizza();
        pizza.setName("Margo");
        pizza.setPrice(120.3);
        pizza.setType(Pizza.PizzaType.SEA);

        Address address = new Address();
        address.setCity("Kyiv");
        address.setState(new State("Created"));

        Customer customer = new Customer();
        customer.setName("Andrii");
        customer.setstateAddress(Arrays.asList(address));
        customer.setPhones(new HashSet<>(Arrays.asList("123", "456")));

        address.setCustomer(customer);
        Customer customer1;
        //customer.setPhones(Arrays.asList("123", "456"));
        try {
            em.getTransaction().begin();
            //em.persist(address);
            //em.persist(customer);            
            em.getTransaction().commit();

            System.out.println(address.getCustomer());

            System.out.println(address.getId());
            em.clear();
            //em.detach(customer);

            customer1 = em.find(Customer.class, 10);
            em.detach(customer1);
            System.out.println(customer1.getPhones());
            //customer = em.merge(customer);
            //em.refresh(address);
            //TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c", Customer.class);
            //customers = tq.getResultList();

            //System.out.println(customers.get(0).getAddress());
        } finally {
            em.close();
            emf.close();
        }
        
    }
}
