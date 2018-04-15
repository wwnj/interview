package multiThread;

/**
 * Created by wb on 2018/4/13.
 */
public class MultiThreadShareData {

    public static void main(String[] args){
        Business2 business = new Business2();
        for(int i=0;i<2;i++)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        business.increment();
                    }
                }
            }).start();
        for(int i=0;i<2;i++)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        business.decrement();
                    }
                }
            }).start();
    }


}
class Business2{

    private int j = 0;

    public synchronized void increment(){
        ++j;
        System.out.println(Thread.currentThread().getName() + "has increment one,j=" + j);
    }
    public synchronized void decrement(){
        --j;
        System.out.println(Thread.currentThread().getName() + "has decrement one,j=" + j);
    }
}
