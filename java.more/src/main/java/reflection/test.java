package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wangtian9 on 2017/8/3.
 */
class test {
    public static void main(String[] args) {
        //创建类的实例
        java.lang.Class c = null;
        Customer customer = null;
        try {
            c = Customer.class;
            Method m1 = c.getDeclaredMethod("getInstance");
            m1.setAccessible(true);//这句至关重要，不设置为true，便无法获取私有方法
            customer = (Customer) m1.invoke(c);
        } catch (Exception e) {
        }

        try {
            java.lang.reflect.Field fieldId = customer.getClass().getDeclaredField("id");//获取私有成员变量id

            //获取私有方法setId(int id)
            String firstLetter = fieldId.getName().substring(0, 1).toUpperCase();
            String setName = "set" + firstLetter + fieldId.getName().substring(1);
            String getName = "get" + firstLetter + fieldId.getName().substring(1);
            java.lang.reflect.Method setMethod = customer.getClass().getDeclaredMethod(setName,long.class);
            Method getMethod = customer.getClass().getDeclaredMethod(getName);
            setMethod.setAccessible(true);//使私有方法可以被获取到
            setMethod.invoke(customer, new Object[]{23});//调用该私有方法并传递数据

            System.out.println("-------------通过公共方法获取到的id值：" + customer.getId());
            System.out.println("-------------通过反射获取到的id值：" + getMethod.invoke(customer));

            //下面将模仿上面的这一段代码，通过反射来分别为name和age这两个私有成员变量赋值
            Field fieldName = customer.getClass().getDeclaredField("name");
            firstLetter = fieldName.getName().substring(0, 1).toUpperCase();
            setName = "set" + firstLetter + fieldName.getName().substring(1);
            setMethod = customer.getClass().getDeclaredMethod(setName, new Class[]{fieldName.getType(),String.class});
            setMethod.setAccessible(true);
            setMethod.invoke(customer, "张三","hh");
            System.out.println("-----------------姓名：" + customer.getName());

            Field fieldAge = customer.getClass().getDeclaredField("age");
            firstLetter = fieldAge.getName().substring(0, 1).toUpperCase();
            setName = "set" + firstLetter + fieldAge.getName().substring(1);
            setMethod = customer.getClass().getDeclaredMethod(setName, new Class[]{fieldAge.getType()});
            setMethod.setAccessible(true);
            setMethod.invoke(customer, "40");
            System.out.println("-----------------年龄：" + customer.getAge());
        } catch (Exception e) {
        }
    }


    //test
}
