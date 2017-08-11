package multithread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangtian9 on 2017/8/11.
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();//阻塞当前线程直到计数器变为0
        System.out.println("3");
    }
}
