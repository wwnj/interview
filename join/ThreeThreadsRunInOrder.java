package multiThread.join;

/**
 * 线程t1、t2、t3按照t1、t2、t3顺序执行
 * Created by wb on 2018/7/17.
 */
public class ThreeThreadsRunInOrder {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName()+" is started");

        Thread t1 = new Thread("t1"){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+" is started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is completed");
            }
        };

        Thread t2 = new Thread("t2"){
            @Override
            public void run(){
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is completed");
            }
        };

        Thread t3 = new Thread("t3"){
            @Override
            public void run(){
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is completed");
            }
        };

        t1.start();
        t2.start();
        t3.start();
        System.out.println(Thread.currentThread().getName()+" is completed");
    }
}
