package multiThread;

/**
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，
 * 接着再回到主线程又循环100次，如此循环50次
 * Created by wb on 2018/3/12.
 */
public class TwosThreadSwith {

    public static void main(String[] args){
        new TwosThreadSwith().init();
    }

    public void init(){
        final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<50;i++){
                            business.SubThread(i);
                        }
                    }
                }).start();
        for (int i=0;i<50;i++){
            business.MainThread(i);
        }
    }

    private class Business{
        boolean bShouldSub = true;//这里相当于定义了控制该谁执行的一个信号灯

        public synchronized void MainThread(int i){
            if(bShouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j=0;j<5;j++){
                System.out.println(Thread.currentThread().getName()+":i="+i+",j="+j);
            }
            bShouldSub=true;
            this.notify();
        }

        public synchronized void SubThread(int i){
            if(!bShouldSub){
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            for(int j=0;j<10;j++){
                System.out.println(Thread.currentThread().getName()+":i="+i+",j="+j);
            }
            bShouldSub=false;
            this.notify();
        }
    }
}
