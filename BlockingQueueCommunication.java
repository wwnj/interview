package multiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wb on 2018/4/15.
 */
public class BlockingQueueCommunication {

    private static Business business = new Business();

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++)
                    business.sub(i);
            }
        }).start();
        for (int i=1;i<=50;i++)
            business.main(i);
    }

    static class Business{
        BlockingQueue<Integer> blockingQueue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Integer> blockingQueue2 = new ArrayBlockingQueue<>(1);
        public Business(){
            try {
                blockingQueue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void sub(int i){
            try{
                blockingQueue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=1;j<=10;j++)
                System.out.println("sub thread sequence of j="+j+",loop of i="+i);
            try{
                blockingQueue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void main(int i){
            try{
                blockingQueue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=1;j<=20;j++)
                System.out.println("main thread sequence of j="+j+",loop of i="+i);
            try {
                blockingQueue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
