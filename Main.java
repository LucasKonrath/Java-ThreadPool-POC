import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        try(IThreadPool threadPool = new FixedSizeThreadPool(3, 2000)) {
            for (int i = 0; i < 10; i++) {
                final int finalI = i;
                threadPool.submitTask(() -> {
                    System.out.println("Starting task");
                    Thread.sleep(1000l);
                    System.out.println("Ending task: " + finalI);
                    return UUID.randomUUID();
                });
                threadPool.submitTask(new TestClass());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}