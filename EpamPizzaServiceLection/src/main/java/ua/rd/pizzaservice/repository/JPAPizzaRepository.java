package ua.rd.pizzaservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
@Repository("pizzaRepository")
@Transactional
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;
//    @PersistenceUnit()
//    private EntityManagerFactory emf;

    @Override
    @Transactional(readOnly = true)
    public Pizza getPizzaByID(Integer id) {
        return em.find(Pizza.class, id);
    }

    @Override    
    public Pizza create(Pizza pizza) {
        em.persist(pizza);
        return pizza;
    }

}
