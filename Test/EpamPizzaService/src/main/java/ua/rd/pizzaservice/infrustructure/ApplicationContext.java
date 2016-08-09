package ua.rd.pizzaservice.infrustructure;

/**
 *
 * @author andrii
 */
public interface ApplicationContext {

    public Object getBean(String bean) throws Exception ;
    
}
