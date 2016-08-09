package pizzaservice.domain.discount;


import pizzaservice.domain.order.Order;

public interface Discount {

	Double calculateDiscount(Order order);

	Boolean isAppliable(Order order);
}
