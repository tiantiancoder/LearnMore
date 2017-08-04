package proxy.dynamic.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.*;

/**
 * Created by wangtian9 on 2017/8/4.
 */
interface Subject {
    public void doSomething();
}

class RealSubject implements Subject {
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}

public class ProxyHandler implements InvocationHandler {
    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在转调具体目标对象之前，可以执行一些功能处理

        //转调具体目标对象的方法
        return method.invoke(proxied, args);

        //在转调具体目标对象之后，可以执行一些功能处理
    }

    public static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Subject.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        RealSubject real = new RealSubject();
        ProxyHandler proxyHandler = new ProxyHandler(real);
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class}, proxyHandler);

        proxySubject.doSomething();

        //write proxySubject class binary data to file
        createProxyClassFile();
    }
}
