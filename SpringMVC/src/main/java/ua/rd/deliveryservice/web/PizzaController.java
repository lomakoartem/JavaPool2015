package ua.rd.deliveryservice.web;

import java.beans.PropertyEditorSupport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.service.PizzaService;

/**
 *
 * @author andrii
 */
@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("pizzas", pizzaService.findAll());
        return "pizzas";
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    @ResponseBody
    public String viewById(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        Pizza pizza = pizzaService.find(pizzaId);
        return pizza.toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String add(@ModelAttribute Pizza pizza) {
        System.out.println(pizza);
        pizzaService.save(pizza);
        return "redirect:pizzas";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit() {
        return "newpizza";
    }

    
}
