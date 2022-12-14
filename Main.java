import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //create queue size - 200
        //Number of threads - 4
        ThreadPool threadPool = new ThreadPool(2,2000);

        System.out.println("Stop here to test");

        for(int i = 0; i < 1000; i++){
            threadPool.submitTask(() -> {
                try {
                    Thread.sleep(1l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Ending task " + UUID.randomUUID());
            });
        }

    }
}