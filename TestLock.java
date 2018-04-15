package multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wb on 2018/3/11.
 */
public class TestLock {

    private int j;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args){
        TestLock testLock = new TestLock();
        for(int i=0;i<2;i++){
            new Thread(testLock.new Adder()).start();
            new Thread(testLock.new Subtractor()).start();
        }
    }

    private class Subtractor implements Runnable{

        @Override
        public void run() {
            while(true){
                /*synchronized (TestLock.this){
                    System.out.println("j--="+j--);
                }*/
                //不使用synchronized同步，而是用Lock替代，需手动释放锁
                lock.lock();
                try{
                    System.out.println("j--="+j--);
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Adder implements Runnable{

        @Override
        public void run() {
            while(true){
                /*synchronized (TestLock.this){
                    System.out.println("j++="+j++);
                }*/
                //不使用synchronized同步，而是用Lock替代，需手动释放锁
                lock.lock();
                try{
                    System.out.println("j++="+j++);
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
