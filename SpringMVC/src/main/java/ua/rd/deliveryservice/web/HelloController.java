package ua.rd.deliveryservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andrii
 */
@Controller
@SessionAttributes("name")
public class HelloController {

    @ModelAttribute("name")
    @RequestMapping(value = "/Hello", method = RequestMethod.GET)
    public String createHelloString() {
        return "Hello from SpringMVC";
    }
    
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public ModelAndView handleRequest(@ModelAttribute("helloStr") String str){
//        ModelAndView mav = new ModelAndView("Hello");
//        mav.addObject("name", str);
//        System.out.println("Controller called");       
//        return mav;
//    }   
    
}