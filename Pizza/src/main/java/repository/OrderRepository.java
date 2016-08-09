package repository;

import Entity.Order;

/**
 * Created by lomak on 30.03.2016.
 */
public interface OrderRepository {
  Long saveOrder(Order order);
}
