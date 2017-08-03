package dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;

public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        /*MethodType：代表“方法类型”，包含了方法的返回值（methodType（）的第一个参数）和具体参数（methodType（）第二个及以后的参数）*/
        MethodType mt = MethodType.methodType(void.class, String.class);
        /*lookup（）方法来自于MethodHandles.lookup，这句的作用是在指定类中查找符合给定的方法名称、 方法类型，并且符合调用权限的方法句柄*/
        /*因为这里调用的是一个虚方法，按照Java语言的规则，方法第一个参数是隐式的，代表该方法的接收者，
        也即是this指向的对象，这个参数以前是放在参数列表中进行传递的，而现在提供了bindTo（）方法来完
        成这件事情*/
        return lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }

    public static List<Integer> transform(List<Integer> dataList, MethodHandle handle) throws Throwable {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.set(i, (Integer) handle.invoke(dataList.get(i)));//invoke
        }
        return dataList;
    }

    public static int doubleVal(int val) {
        return val * 2;
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        /*无论obj最终是哪个实现类，下面这句都能正确调用到println方法*/
        getPrintlnMH(obj).invokeExact("icyfenix");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findStatic(MethodHandleTest.class, "doubleVal", MethodType.methodType(int.class, int.class));
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        MethodHandleTest.transform(dataList, mh);// 方法做为参数
        for (Integer data : dataList) {
            System.out.println(data);//2,4,6,8,10
        }
    }
}