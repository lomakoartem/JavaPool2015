package ua.rd.deliveryservice.infrastructure;

/**
 *
 * @author andrii
 */
public class InitialContext {

    private final Config config = new JavaConfig();

    public InitialContext() {
    }

    public <T> T lookup(String name) throws Exception {
        Class<T> type = config.getImpl(name);
        T obj = type.newInstance();
        
        return obj;
    }
}
