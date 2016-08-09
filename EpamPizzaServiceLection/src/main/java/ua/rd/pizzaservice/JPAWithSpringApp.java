package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
public class JPAWithSpringApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext(
                        "repositoryMySQLContext.xml"
                );

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"}, false
                );
        appContext.setParent(repositoryContext);        
        appContext.refresh();
        
        PizzaRepository pr
                = (PizzaRepository) appContext.getBean("pizzaRepository");
        Pizza pizza = new Pizza();
        pizza.setName("Marg");
        pizza.setPrice(123.4);
        pizza.setType(Pizza.PizzaType.SEA);
        pizza = pr.create(pizza);
        
        System.out.println(pr.getPizzaByID(pizza.getId()));
        
        appContext.close();
        repositoryContext.close();
    }
}
