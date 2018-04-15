package multiThread;

import java.util.Random;

/**
 * Created by wb on 2018/4/13.
 */
public class ThreadShareData {

    private int data;

    public static void main(String[] args){
        for (int i=0; i<2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadShareData threadShareData = new ThreadShareData();
                    threadShareData.data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() +
                            " has put data:" + threadShareData.data);
                    threadShareData.new A().get();
                    threadShareData.new B().get();
                }
            }).start();
        }
    }

    class A{
        public void get(){
            System.out.println("A from " + Thread.currentThread().getName() +
                " get data:" + data);
        }
    }

    class B{
        public void get(){
            System.out.println("B from " + Thread.currentThread().getName() +
                    " get data:" + data);
        }
    }
}
