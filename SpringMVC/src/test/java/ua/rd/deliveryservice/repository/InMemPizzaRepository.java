package ua.rd.deliveryservice.repository;

import java.util.List;
import java.util.Map;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.infrastructure.Benchmark;
import ua.rd.deliveryservice.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
public class InMemPizzaRepository implements PizzaRepository {

    private Map<Integer, Pizza> pizzas;

    public void init() {
        System.out.println("Init() PizzaInMem");
    }

    public Map<Integer, Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Integer, Pizza> pizzas) {
        this.pizzas = pizzas;
    }    
    
    @Override
    @Benchmark
    public Pizza find(Integer id) {
        return pizzas.get(id);
    }

    @Override
    public Pizza save(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pizza> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
