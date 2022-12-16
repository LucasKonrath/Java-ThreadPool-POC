import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FixedSizeThreadPool implements IThreadPool {
    BlockingQueue<Task> taskExecutors;

    List<ThreadTaskExecutor> threads;

    public FixedSizeThreadPool(int pool, int queueCapacity) {
        taskExecutors = new ArrayBlockingQueue<>(queueCapacity);
        threads = new ArrayList<>();
        createThreads(pool);
    }

    public void submitTask(Task runnable) {
        taskExecutors.add(runnable);
    }

    private void createThreads(int quantityOfThreads) {
        for (int i = 0; i < quantityOfThreads; i++) {
            ThreadTaskExecutor taskExecutor = new ThreadTaskExecutor(taskExecutors);
            threads.add(taskExecutor);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    @Override
    public void close() throws Exception {
        boolean anyRunning;
        System.out.println("Invoked destroy method");

        do {

            threads.forEach(ThreadTaskExecutor::tryStop);

            anyRunning = threads.stream()
                    .anyMatch(ThreadTaskExecutor::isRunning);


        } while (anyRunning);

        System.out.println("Destroyed all threads");
    }
}
