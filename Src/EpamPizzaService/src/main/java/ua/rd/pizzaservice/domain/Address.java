package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lomak on 20.04.2016.
 */
@Entity
public class Address implements Serializable {

  @Column(name = "ADDR_ID")
    @Id
    private Integer id;


    private String city;

    private State state;

    public Address(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
