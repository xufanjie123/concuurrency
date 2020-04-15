package com.example.concuurrency.proxy.jdk;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xufanjie
 * @date 2020/4/15
 */
@Data
public class JdkProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before Proxy");
        Object res = method.invoke(target, objects);
        System.out.println("after Proxy");
        return res;
    }

}
