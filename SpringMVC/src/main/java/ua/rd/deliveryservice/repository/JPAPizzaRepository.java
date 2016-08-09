package ua.rd.deliveryservice.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.deliveryservice.domain.Pizza;

/**
 *
 * @author andrii
 */
@Repository
public class JPAPizzaRepository implements PizzaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly = true)
    public Pizza find(Integer id) {       
        return em.find(Pizza.class, id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
       
        if (pizza.getPizzaId() == null) {
            em.persist(pizza);
        } else {
            em.merge(pizza);
        }
        return pizza;
    }

    @Override
    public List<Pizza> findAll() {
        return em.createQuery("from Pizza", Pizza.class).getResultList();
    
    }
    
}
