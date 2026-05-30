import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SharedResource {

    int maxCapacity = 5; // to maintain fixed size
    int N = 10;

    boolean produced = false;

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(maxCapacity);

    int num = 1;
    public synchronized void addItem() {

        while (num <= N) {
            while (queue.size() == maxCapacity) {
                try {
                    System.out.println("Queue is full");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("Adding " + num);
            queue.add(num);
            num++;
            notifyAll();
        }

        produced = true;
        notifyAll();
    }

    public synchronized void removeItem() {

        while (true) {
            while (queue.isEmpty()) {
                if (produced) {
                    return;
                }

                try {
                    System.out.println("Queue is empty");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Removing item " + queue.peek());
            queue.remove();
            notifyAll();
        }
    }
}