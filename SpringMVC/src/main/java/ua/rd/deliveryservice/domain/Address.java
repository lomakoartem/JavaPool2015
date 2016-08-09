package ua.rd.deliveryservice.domain;

/**
 *
 * @author andrii
 */
public class Address {
    private String city;

    public Address(String city) {
        this.city = city;
    }
    
    public void destroy() {
        System.out.println("Destroy Add");
    }
}