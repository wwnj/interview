package multiThread;

/**
 * Created by wb on 2018/3/11.
 */
public class TestSynchronized {

    static TestSynchronized test = new TestSynchronized();

    synchronized static void sayHello(){
        System.out.println("hello world!");
    }
    synchronized void sayBye(){
        System.out.println("Bye bye!");
    }

    public static void main(String[] args){
        new Thread(new Thread1()).start();
    }

    private static class Thread1 implements Runnable{

        @Override
        public void run() {
            test.sayBye();
        }
    }
}
