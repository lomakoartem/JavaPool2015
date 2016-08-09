package pizzaservice.service.discountService;


import pizzaservice.domain.order.Order;

public interface DiscountService {

	Double calculateFinalDiscountAmount(Order order);

	Double calculateDiscountsAmount(Order order);

	Double calculatePriceWithDiscounts(Order order);
}
