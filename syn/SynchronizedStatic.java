package multiThread.syn;

/**
 * synchronized static 和 synchronized锁住不是同一对象，不能同步
 * Created by wb on 2018/7/24.
 */
public class SynchronizedStatic {

    public synchronized static void synStaticMethod(){
        System.out.println("synchronized static method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void synMethod(){
        System.out.println("synchronized method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SynchronizedStatic syn = new SynchronizedStatic();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synStaticMethod();
                //syn.synMethod();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syn.synMethod();
            }
        }).start();
    }
}
