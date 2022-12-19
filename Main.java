import framework.pool.FixedSizeThreadPool;
import framework.pool.ThreadPool;
import framework.tasks.HelloWorldTask;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        try(ThreadPool threadPool = new FixedSizeThreadPool(3, 2000)) {
            for (int i = 0; i < 10; i++) {
                final int finalI = i;
                threadPool.submitTask(() -> {
                    System.out.println("Starting task");
                    Thread.sleep(1000l);
                    System.out.println("Ending task: " + finalI);
                    return UUID.randomUUID();
                });

                threadPool.submitTask(new HelloWorldTask());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}