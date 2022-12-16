import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //create queue size - 200
        //Number of threads - 4
        try(IThreadPool threadPool = new FixedSizeThreadPool(3, 2000)) {
            for (int i = 0; i < 10; i++) {
                final int finalI = i;
                threadPool.submitTask(() -> {
                    System.out.println("Starting task");
                    Thread.sleep(1000l);
                    System.out.println("Ending task: " + finalI);
                    return UUID.randomUUID();
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}