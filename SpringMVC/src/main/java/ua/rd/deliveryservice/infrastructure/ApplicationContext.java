package ua.rd.deliveryservice.infrastructure;

/**
 *
 * @author andrii
 */
public interface ApplicationContext {

    public Object getBean(String beanName) throws Exception;
    
}
