package multiThread.syn;

/**
 * 同步代码块中抛出异常，锁是能释放的
 * Created by wb on 2018/7/24.
 */
public class SynchronizedException {

    public synchronized void synMethod(){
        System.out.println("synchronized method");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i=1/0;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynchronizedException syn = new SynchronizedException();
        for(int i=0;i<2;++i)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syn.synMethod();
                }
            },"thread "+i).start();
    }
}
