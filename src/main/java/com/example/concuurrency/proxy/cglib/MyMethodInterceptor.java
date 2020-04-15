package com.example.concuurrency.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xufanjie
 * @date 2020/4/15
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before proxy");
        Object object = methodProxy.invokeSuper(o,objects);
//        Object object = method.invoke(o,objects);
        System.out.println("after proxy");
        return object;
    }
}
