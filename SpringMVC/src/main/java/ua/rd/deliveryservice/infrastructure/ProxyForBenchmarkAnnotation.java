package ua.rd.deliveryservice.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author andrii
 */
class ProxyForBenchmarkAnnotation {

    Object proxyObjForBenchmarkAnnotation(Object bean) throws IllegalArgumentException, SecurityException {
        Class<?> clazz = bean.getClass();
        System.out.println(clazz);

        for (Method m : clazz.getMethods()) {
            if (m.isAnnotationPresent(Benchmark.class)) {
                return createProxyObj(bean);
            }

        }
        return bean;

    }

    private Object createProxyObj(final Object proxied) throws IllegalArgumentException {
      
        boolean flag = false;
        for (Class<?> c : proxied.getClass().getInterfaces()) {
            if (c.getName().equals("org.springframework.cglib.proxy.Factory")) {
                flag = true;
                break;
            }
        }
       
        final Class<?> type = flag ? proxied.getClass().getSuperclass() : proxied.getClass();

        return Proxy.newProxyInstance(type.getClassLoader(),
                type.getInterfaces(),
                new InvocationHandler() {

                    @Override
                    public Object invoke(
                            Object proxy,
                            Method method,
                            Object[] args) throws Throwable {
                                System.out.println("Called: " + method.getName());
                                if (type.getMethod(method.getName(), method.getParameterTypes())
                                .isAnnotationPresent(Benchmark.class)) {

                                    System.out.println("Benchmark start: "
                                            + method.getName());
                                    long start = System.nanoTime();
                                    Object retVal = method.invoke(proxied, args);
                                    long result = System.nanoTime() - start;
                                    System.out.println(result);
                                    System.out.println("Benchmark finish: "
                                            + method.getName());
                                    return retVal;
                                } else {
                                    return method.invoke(proxied, args);
                                }
                            }
                });

    }
}