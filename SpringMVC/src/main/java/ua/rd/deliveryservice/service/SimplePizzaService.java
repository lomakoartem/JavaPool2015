package ua.rd.deliveryservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
@Service
public class SimplePizzaService implements PizzaService {

    
    private final PizzaRepository pizzaRepository;    
    
    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
       this.pizzaRepository = pizzaRepository;
    }
            

    @Override
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public List<Pizza> findAll() {
     return  pizzaRepository.findAll();
    }

}
