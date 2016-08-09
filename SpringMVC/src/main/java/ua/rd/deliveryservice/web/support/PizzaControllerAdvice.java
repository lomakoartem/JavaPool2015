package ua.rd.deliveryservice.web.support;

import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.service.PizzaService;

/**
 *
 * @author andrii
 */
@ControllerAdvice()
public class PizzaControllerAdvice {
    
    @Autowired
    private PizzaService pizzaService;
    
    @ModelAttribute("pizza")
    public Pizza findPizza(
            @RequestParam(value = "pizzaId", required = false) 
                    Pizza pizza, BindingResult br) {
//        Pizza pizza = new Pizza();
//        if (pizzaId != null) {
//            pizza = pizzaService.find(pizzaId);
//        }
        if (br.hasErrors()) {
            System.out.println(br.getAllErrors());
        }
        
        System.out.println("Find: " + pizza);
        return pizza;
    }

    //@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class, 
                new PropertyEditorSupport() {

            @Override
            public void setAsText(String pizzaId) throws IllegalArgumentException {
                Pizza pizza;
                if ((pizzaId == null) || pizzaId.isEmpty()) {
                    pizza = new Pizza();
                } else {
                    pizza = pizzaService.find(Integer.valueOf(pizzaId));
                }
                setValue(pizza);
            }

        });
    }
}
