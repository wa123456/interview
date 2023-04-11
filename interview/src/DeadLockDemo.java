/**
 * 死锁小Demo
 * 死锁是指两个或多个以上的进程在执行过程中，
 * 因争夺资源而造成一种互相等待的现象，
 * 若无外力干涉那他们都将无法推进下去
 * @author: 陌溪
 * @create: 2020-03-18-17:58
 */

import java.util.concurrent.TimeUnit;

/**
 * 资源类
 */
class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    // 持有自己的锁，还想得到别人的锁

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            //这里写变量好；
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获取：" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //因为是占有并并等待，所以在外层synchronized
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获取：" + lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        //不能加 因为局部变量本身就有着访问权限的设定。 只能在局部调用，也就是说局部变量的生命周期在{}
        // 之中除了这个方法外界是没办法访问你这个变量，所以不需要用任何修饰符修饰，比如private ,public protected等。
        //private String lockA = "lockA";
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "t1").start();

        new Thread(new HoldLockThread(lockB, lockA), "t2").start();
    }
}