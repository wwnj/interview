package multiThread.join;

/**
 * Created by wb on 2018/7/17.
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" is started");
        Thread exampleThread = new Thread(){
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

        exampleThread.start();
        exampleThread.join();

        System.out.println(Thread.currentThread().getName()+" is completed");
    }
}
