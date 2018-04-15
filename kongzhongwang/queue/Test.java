package multiThread.kongzhongwang.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wb on 2018/4/15.
 */
public class Test {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        final Integer[] index = {0};
        for(int i=0;i<10;i++){  //这行不能改动
            String input = i+"";  //这行不能改动
            /*String output = TestDo.doSome(input);
            System.out.println(Thread.currentThread().getName()+ ":" + output);*/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try{
                        while(!Thread.currentThread().getName().equals(Integer.toString(index[0]))){
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName()+ ":" + output);
                        index[0]++;
                        condition.signalAll();
                    }finally {
                        lock.unlock();
                    }
                }
            },Integer.toString(i)).start();
        }
    }
}

//不能改动此TestDo类
class TestDo {
    public static String doSome(String input){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":"+ (System.currentTimeMillis() / 1000);
        return output;
    }
}

