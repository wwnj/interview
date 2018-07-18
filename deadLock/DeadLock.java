package multiThread.deadLock;

/**
 * Created by wb on 2018/7/15.
 */
public class DeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    static class Lock1 implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Lock1: ");
                    synchronized (lock1) {
                        System.out.println("Lock1获取到lock1");
                        Thread.sleep(3000);
                        synchronized(lock2){
                            System.out.println("Lock1获取到lock2");
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class Lock2 implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Lock2:");
                    synchronized (lock2) {
                        System.out.println("Lock2获取到lock2");
                        Thread.sleep(3000);
                        synchronized(lock1){
                            System.out.println("Lock2获取到lock1");
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}
