import java.util.*;

public class SharedResource {

    int N = 10; // to maintain fixed size

    boolean produced = false;

    Queue<Integer> queue = new LinkedList<Integer>();

    int num = 1;
    public synchronized void addItem() {

        while (num <= N) {
            while (queue.size() == N) {
                try {
                    System.out.println("Queue is full");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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