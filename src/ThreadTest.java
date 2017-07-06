/**
 * Created by KING on 2017/7/6.
 */
public class ThreadTest {
    private Object lock  =new Object();
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.new ThreadA().start();
        threadTest.new ThreadB().start();
    }

    class ThreadA extends  Thread{
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 'a';i <= 'z';i++){
                    System.out.print((char)i);
                    lock.notify();
                    try {
                        lock.wait();
                    }catch(InterruptedException e){
                    }
                }
                lock.notifyAll();
            }
        }
    }

    class ThreadB extends Thread{
        @Override
        public void run() {
            synchronized (lock) {
                for (int i =1; i <= 26;i++){
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    }catch(InterruptedException e){
                    }
                }
                lock.notifyAll();
            }
        }
    }
}
