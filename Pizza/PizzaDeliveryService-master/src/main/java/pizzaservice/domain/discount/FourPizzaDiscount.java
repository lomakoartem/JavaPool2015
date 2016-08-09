package pizzaservice.domain.discount;

import pizzaservice.domain.order.Order;
import pizzaservice.domain.pizza.Pizza;

import java.util.List;



public class FourPizzaDiscount implements Discount {

	protected static final Double DEFAULT_DISCOUNT_AMOUNT_FOR_UNAPPLIABLE = 0d;
	protected static final int PIZZA_MIN_COUNT_FOR_DISCOUNT = 4;
	protected static final double DISCOUNT_PERCENTAGE_FOR_MAX_PRICED_PIZZA = 0.3d;

	@Override
	public Double calculateDiscount(Order order) {
		List<Pizza> pizzas = order.getPizzas();
		if (!isAppliable(pizzas)) {
			return DEFAULT_DISCOUNT_AMOUNT_FOR_UNAPPLIABLE;
		}
		Double maxPizzaPrice = getMaxPizzaPrice(pizzas);
		Double discountAmount = calculateDiscountAmount(maxPizzaPrice);
		return discountAmount;

	}

	@Override
	public Boolean isAppliable(Order order) {
		List<Pizza> pizzas = order.getPizzas();
		return isAppliable(pizzas);
	}

	private Boolean isAppliable(List<Pizza> pizzas) {
		Boolean result = pizzas.size() >= PIZZA_MIN_COUNT_FOR_DISCOUNT;
		return result;
	}

	private Double getMaxPizzaPrice(List<Pizza> pizzas) {
		double maxPrice = 0d;
		for (Pizza pizza : pizzas) {
			if (pizza.getPrice() > maxPrice) {
				maxPrice = pizza.getPrice();
			}
		}
		return maxPrice;
	}

	private Double calculateDiscountAmount(Double maxPizzaPrice) {
		return DISCOUNT_PERCENTAGE_FOR_MAX_PRICED_PIZZA * maxPizzaPrice;
	}
}
