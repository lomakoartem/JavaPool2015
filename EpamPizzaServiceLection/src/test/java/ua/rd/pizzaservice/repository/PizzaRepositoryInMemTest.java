package ua.rd.pizzaservice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {     
    "classpath:/repositoryH2Context.xml"}
)
public class PizzaRepositoryInMemTest extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Test
    public void testGetPizzaByID() {
        final String sql
                = "INSERT INTO pizza (name, pizza_type) VALUES ('Vegan', "
                + "'1')";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {

                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    }
                }, keyHolder);

        Integer id = keyHolder.getKey().intValue();
        
        Pizza pizza = pizzaRepository.getPizzaByID(id);
        System.out.println(pizza);
    }
    
    @Test
    public void testCreate() {
        Pizza pizza = new Pizza();
        pizza.setName("Marg");
        pizza.setPrice(123.4);
        pizza.setType(Pizza.PizzaType.SEA);
        
        pizza = pizzaRepository.create(pizza);
        Assert.assertNotNull(pizza.getId());
        System.out.println(pizza);
    }
    
}
