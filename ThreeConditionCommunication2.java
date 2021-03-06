package multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wb on 2018/4/14.
 */
public class ThreeConditionCommunication2 {

    public static void main(String[] args){
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++)
                    business.one(i);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++)
                    business.two(i);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=50;i++)
                    business.three(i);
            }
        }).start();
    }

    static class Business{
        Lock lock = new ReentrantLock();
        Condition conditionOne = lock.newCondition();
        Condition conditionTwo = lock.newCondition();
        Condition conditionThree = lock.newCondition();
        private int should = 1;
        public void one(int i){
            lock.lock();
            try{
                while(should != 1){
                    try {
                        conditionOne.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=10;j++)
                    System.out.println("first thread sequence of j="+j+",loop of i="+i);
                should = 2;
                conditionTwo.signal();
            }finally{
                lock.unlock();
            }
        }
        public void two(int i){
            lock.lock();
            try{
                while(should != 2){
                    try {
                        conditionTwo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=20;j++)
                    System.out.println("second thread sequence of j="+j+",loop of i="+i);
                should = 3;
                conditionThree.signal();
            }finally{
                lock.unlock();
            }
        }
        public void three(int i){
            lock.lock();
            try{
                while(should != 3){
                    try {
                        conditionThree.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=1;j<=30;j++)
                    System.out.println("third thread sequence of j="+j+",loop of i="+i);
                should = 1;
                conditionOne.signal();
            }finally{
                lock.unlock();
            }
        }
    }
}
