package ua.rd.deliveryservice.web.support;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author andrii
 */
@ControllerAdvice()
public class ExceptionsControllerAdivce {

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String error(Exception e, Model model) {
//        model.addAttribute("ex", e);
//        return "error";
//    }

}
