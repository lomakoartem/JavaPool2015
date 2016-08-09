package pizzaservice.service.discountService;

import org.springframework.stereotype.Repository;
import pizzaservice.domain.discount.Discount;
import pizzaservice.domain.discount.FourPizzaDiscount;
import pizzaservice.domain.order.Order;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemDiscountProvider implements DiscountProvider {

	private List<Discount> discounts = new ArrayList<>();

	@PostConstruct
	public void determineDiscounts() {
	    discounts.add(new FourPizzaDiscount());
	}

	@Override
	public List<Discount> getAppliableDiscounts(Order order) {
		List<Discount> appliableDiscounts = new ArrayList<>();
		for (Discount discount : discounts) {
			if (discount.isAppliable(order)) {
				appliableDiscounts.add(discount);
			}
		}
		if (appliableDiscounts.isEmpty()) {
			appliableDiscounts = java.util.Collections.emptyList();
		}
		return appliableDiscounts;
	}

}
