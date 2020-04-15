package com.example.concuurrency.proxy;

import com.example.concuurrency.proxy.cglib.MyMethodInterceptor;
import com.example.concuurrency.proxy.cglib.Subject;
import com.example.concuurrency.proxy.jdk.ISubject;
import com.example.concuurrency.proxy.jdk.ISubjectImpl;
import com.example.concuurrency.proxy.jdk.JdkProxy;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author xufanjie
 * @date 2020/4/15
 */
public class ApoTest {

    public static void main(String[] args) {
        ISubject jdkProxy = getJdkProxy();
        ISubject cglibProxy = getCglibProxy();
        for (int i = 0; i < 10000; i++) {
            jdkProxy.request();
            cglibProxy.request();
        }
        long st = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            cglibProxy.request();

        }
        long ed = System.currentTimeMillis();
        long cglibTime = ed - st;
        st = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            jdkProxy.request();
        }
        ed = System.currentTimeMillis();
        System.out.println("cglib:" + cglibTime);
        System.out.println("jdk:" + (ed - st));
    }

    public static ISubject getJdkProxy() {
        ISubject iSubject = new ISubjectImpl();
        JdkProxy jdkProxy = new JdkProxy();
        jdkProxy.setTarget(iSubject);
        ISubject proxyInstance = (ISubject) Proxy.newProxyInstance(iSubject.getClass().getClassLoader(), iSubject.getClass().getInterfaces(), jdkProxy);
        return proxyInstance;
    }

    public static ISubject getCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ISubjectImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());
        ISubject proxy = (ISubject) enhancer.create();
        return proxy;
    }

}
