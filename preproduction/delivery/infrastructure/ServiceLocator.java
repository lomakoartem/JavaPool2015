package com.preproduction.delivery.infrastructure;

/**
 *
 * @author Irbis
 */
public class ServiceLocator {
    private static Config config;
    private static ServiceLocator serviceLocator;

    private ServiceLocator() {
        config = new JavaConfig();
    }

    public static ServiceLocator getInstance(){
        if (serviceLocator == null) {
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }

    public Object getService(String jndiName){
        Object service = null;
        try {
            service = config.getImpl(jndiName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return service;
    }
}
