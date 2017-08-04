package proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangtian9 on 2017/8/4.
 */
public class DynamicProxyTest {
    interface IHello{
        public void sayHello();
    }
    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.print("hello");
        }
    }
    static class DynamicProxy implements InvocationHandler{
        private Object p;

        public Object bind(Object proxy){
            this.p=proxy;
            return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),proxy.getClass().getInterfaces(),this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.print("proxy");
            return method.invoke(p,args);
        }
    }
    public static void main(String[] args){
        IHello h=(IHello)(new DynamicProxy().bind(new Hello()));
        h.sayHello();
    }
}
