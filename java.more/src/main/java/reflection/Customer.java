package reflection;

/**
 * Created by wangtian9 on 2017/8/3.
 */
public class Customer {
    private long id;
    private String name;
    private String age;

    private static Customer instance = null;
    /** 显示将构造函数声明为私有，外界无法通过new来实例化本类 */
    private Customer(){}
    private static synchronized Customer getInstance(){
        if(instance == null){
            return new Customer();
        }
        return instance;
    }

    /** 本set()方法为私有方法，外界无法直接为id属性赋值 */
    private void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    /** 本set()方法为私有方法，外界无法直接为name属性赋值 */
    private void setName(String name,String hh) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    /** 本set()方法为私有方法，外界无法直接为age属性赋值 */
    private void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }
}

