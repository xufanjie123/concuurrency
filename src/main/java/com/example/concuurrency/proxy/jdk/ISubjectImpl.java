package com.example.concuurrency.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author xufanjie
 * @date 2020/4/15
 */
public class ISubjectImpl implements ISubject{

    int a = 1;

    @Override
    public void request() {
        a++;
    }

    public static void main(String[] args) {
        ISubject iSubject = new ISubjectImpl();
        JdkProxy jdkProxy = new JdkProxy();
        jdkProxy.setTarget(iSubject);
        ISubject proxyInstance = (ISubject) Proxy.newProxyInstance(iSubject.getClass().getClassLoader(),iSubject.getClass().getInterfaces(),jdkProxy);
        proxyInstance.request();
    }

}
