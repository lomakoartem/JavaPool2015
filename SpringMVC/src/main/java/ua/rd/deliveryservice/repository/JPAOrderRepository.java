package ua.rd.deliveryservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.deliveryservice.domain.Order;

/**
 *
 * @author andrii
 */
@Repository
public class JPAOrderRepository implements OrderRepository {

    @Override
    public Order save(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
