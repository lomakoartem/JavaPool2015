package ua.rd.deliveryservice.infrastructure;

import org.springframework.beans.factory.FactoryBean;
import ua.rd.deliveryservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public class PizzaFactoryBean implements FactoryBean<Pizza>{

    private Integer id;
    private String name;
    private Pizza.PizzaType type;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Pizza.PizzaType type) {
        this.type = type;
    }    
    
    @Override
    public Pizza getObject() throws Exception {
        return new Pizza(id, name, type);
    }

    @Override
    public Class<?> getObjectType() {
        return Pizza.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
    
}
