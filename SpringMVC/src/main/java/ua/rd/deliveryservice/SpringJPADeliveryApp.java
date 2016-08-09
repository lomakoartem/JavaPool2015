package ua.rd.deliveryservice;

import javax.activation.DataSource;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.repository.PizzaRepository;
import ua.rd.deliveryservice.service.PizzaService;

/**
 *
 * @author andrii
 */
public class SpringJPADeliveryApp {

    public static void main(String[] args) {
     
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext(
                        "repositoryContext.xml", "repositoryInMemDBContext.xml"
                );
        repositoryContext.getEnvironment().setActiveProfiles("prod");
        repositoryContext.refresh();

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"},
                        repositoryContext);

        Pizza pizza = new Pizza();
        pizza.setName("Vegan");
        pizza.setPizzaType(Pizza.PizzaType.VEGETARIAN);

        PizzaService pr = appContext.getBean(PizzaService.class);
        pizza = pr.save(pizza);
        System.out.println(pizza);
        
        repositoryContext.close();
        appContext.close();
        
        DataSourceTransactionManager manager;

    }
}
