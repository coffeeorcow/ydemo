package com.yi.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test1 {
    public static void main(String[] args) {
        TeImpl teImpl = new TeImpl();
        InvocationHandler handler = new InnerTest(teImpl);

        Te t = (Te) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), teImpl.getClass().getInterfaces(), handler);
        System.out.println("result => " + t.greet());

    }
}

interface Te {
    String greet();
}

class TeImpl implements Te {
    public String greet() {
        return "hi";
    }
}

class InnerTest implements InvocationHandler {

    private Object target;

    public InnerTest(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        Object result = method.invoke(target, args);
        System.out.println("调用后");
        return result;
    }
}
