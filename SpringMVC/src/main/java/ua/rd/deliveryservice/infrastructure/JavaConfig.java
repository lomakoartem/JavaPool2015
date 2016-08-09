package ua.rd.deliveryservice.infrastructure;

//import ua.rd.deliveryservice.service.InMemOrderRepository;
//import ua.rd.deliveryservice.service.InMemPizzaRepository;
import ua.rd.deliveryservice.service.SimpleOrderService;
import ua.rd.deliveryservice.service.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrii on 1/24/16.
 */

public class JavaConfig implements Config {
    private Map<String,Class<?>> ifc2Class = new HashMap<>();

    public JavaConfig() {
//        ifc2Class.put("orderRepository", InMemOrderRepository.class);
//        ifc2Class.put("pizzaRepository", InMemPizzaRepository.class);
        ifc2Class.put("orderService", SimpleOrderService.class);
        ifc2Class.put("pizzaService", SimplePizzaService.class);
    }

    public <T> Class<T> getImpl(String ifc) {
        return (Class<T>) ifc2Class.get(ifc);
    }
}
