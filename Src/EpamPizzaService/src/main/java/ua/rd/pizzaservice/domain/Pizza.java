package ua.rd.pizzaservice.domain;


import javax.persistence.*;

/**
 * @author andrii
 */
@Entity
public class Pizza {

    public enum PizzaType {
        SEA, MEAT, VEGAN
    }

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private Double price;
    @Enumerated(EnumType.STRING)
    @Basic()
    @Column (unique = true, name = "pizza_typE")
    private PizzaType type;

    public Pizza() {
    }

    public Pizza(Integer id, String name, Double price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }

}
