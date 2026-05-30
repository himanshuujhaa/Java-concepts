import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionHandler;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2),
                new CustomThreadFactory(), new CustomRejectHandler());

        for(int i=0;i<=10;i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Task processed by: " + Thread.currentThread().getName());
            });
        }
        threadPoolExecutor.shutdown();
    }
}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

class CustomRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Rejected: " + r.toString());
    }
}