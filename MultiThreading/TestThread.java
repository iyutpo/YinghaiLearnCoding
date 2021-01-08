import java.util.Date;
import java.util.concurrent.Semaphore;
import java.text.SimpleDateFormat;  

public class TestThread {

    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    public static void main(String[] args) {
        final Object someObject = new Object();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    System.out.println(now() + " t1 thread is running.");
                    System.out.println(now() + this.getName() + " trying to take object: someObject");
                    synchronized (someObject) {
                        System.out.println(now() + this.getName() + " someObject has been taken.");
                        Thread.sleep(5000);
                        System.out.println(now() + this.getName() + " releasing someObject.");
                    }
                    System.out.println(now() + " t1 thread over.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName(" t1");
        t1.start();

        Thread t2 = new Thread() {
            public void run() {
                try {
                    System.out.println(now() + " t1 thread is running.");
                    System.out.println(now() + this.getName() + " trying to take object: someObject");
                    synchronized (someObject) {
                        System.out.println(now() + this.getName() + " someObject has been taken.");
                        Thread.sleep(5000);
                        System.out.println(now() + this.getName() + " releasing someObject.");
                    }
                    System.out.println(now() + " t2 thread over.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.setName(" t2");
        t2.start();
    }
}
