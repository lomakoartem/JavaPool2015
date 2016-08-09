package Entity;

import java.util.List;

/**
 * Created by lomak on 30.03.2016.
 */
public class Order {
    private Long id;
    private Customer customer;
    private List<Pizza> pizza;

    public Order(Customer customer, List<Pizza> pizza) {
        this.id = id;
        this.customer = customer;
        this.pizza = pizza;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", pizza=" + pizza +
                '}';
    }
}
