package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by lomak on 29.04.2016.
 */
@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public Pizza getPizzaByID(Integer id) {
        return em.find(Pizza.class,id);
    }
}
