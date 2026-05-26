public class SharedResource {

    boolean isItemAvailable = false;

    public synchronized void addItem() {
        isItemAvailable = true;
        System.out.println("Add item");
        notifyAll();
    }

    public synchronized void removeItem() {

        while (!isItemAvailable) {
            try {
                System.out.println("Waiting for add item");
                wait();
                System.out.println("Remove item");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        isItemAvailable = false;
    }

}