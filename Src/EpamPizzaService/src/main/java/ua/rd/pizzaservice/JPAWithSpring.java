package ua.rd.pizzaservice;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * Created by lomak on 29.04.2016.
 */
public class JPAWithSpring {
    public static void main(String[] args) {
        ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
        repContext.refresh();
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "appContext.xml" }, repContext);

        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class);
        Pizza pizza = pizzaRepository.getPizzaByID(1);
        System.out.println(pizza);
    }
}
