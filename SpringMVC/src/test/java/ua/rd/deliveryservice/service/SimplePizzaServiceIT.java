package ua.rd.deliveryservice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.deliveryservice.domain.Pizza;

/**
 *
 * @author andrii
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/repositoryContext.xml",
    "classpath:/repositoryInMemDBContext.xml",
    "classpath:/appContext.xml"
    })
@ActiveProfiles(profiles = "dev")
//@Transactional
//@Rollback
public class SimplePizzaServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PizzaService pizzaService;
    @PersistenceContext
    private EntityManager em;

    @Test    
    public void testFind() {
        System.out.println("find");
        final String sql
                = "INSERT INTO pizzas (pizza_name, pizzaType) VALUES ('Vegan', "
                + "'VEGETARIAN')";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {

                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    }
                }, keyHolder);

        Integer id = keyHolder.getKey().intValue();

        Pizza result = pizzaService.find(id);
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void testSave() {
        System.out.println("save");
        Pizza pizza = new Pizza();
        pizza.setName("Margarita");
        pizza.setPizzaType(Pizza.PizzaType.SEA);

        Pizza result = pizzaService.save(pizza);
        em.flush();
        System.out.println(result.getPizzaId());
        assertNotNull(result.getPizzaId());

    }

}
