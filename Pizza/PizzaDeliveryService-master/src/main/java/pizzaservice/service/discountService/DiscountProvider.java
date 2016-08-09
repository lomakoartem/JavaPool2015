package pizzaservice.service.discountService;


import pizzaservice.domain.discount.Discount;
import pizzaservice.domain.order.Order;

import java.util.List;

public interface DiscountProvider {

	List<Discount> getAppliableDiscounts(Order order);
}
