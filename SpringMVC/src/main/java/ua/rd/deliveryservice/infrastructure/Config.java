package ua.rd.deliveryservice.infrastructure;

/**
 * Created by andrii on 1/24/16.
 */

public interface Config {

    public <T> Class<T> getImpl(String ifc);

}
