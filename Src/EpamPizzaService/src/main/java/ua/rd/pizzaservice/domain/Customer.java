package ua.rd.pizzaservice.domain;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Factory;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author andrii
 */
@Component
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE    )
public class Customer implements FactoryBean<Customer> {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    @OneToOne
    private Address address;
   @Version
    private Integer version;
    @ElementCollection
    private List<String> phones;
    public Customer() {
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
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

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
    }


    @Override
    public Customer getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
