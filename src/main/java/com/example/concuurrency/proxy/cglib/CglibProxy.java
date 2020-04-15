package com.example.concuurrency.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author xufanjie
 * @date 2020/4/15
 */
public class CglibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Subject proxy = (Subject) enhancer.create();
        proxy.request();
    }

}
