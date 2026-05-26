public class Main {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
           sharedResource.addItem();

        });

        Thread consumerThread = new Thread(() -> {
            sharedResource.removeItem();
        });

        producerThread.start();
        consumerThread.start();
    }
}