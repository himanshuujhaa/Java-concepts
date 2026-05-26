public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            sharedResource.addItem();
        });

        Thread consumer = new Thread(() -> {
            sharedResource.removeItem();
        });

        producer.start();
        consumer.start();
    }
}