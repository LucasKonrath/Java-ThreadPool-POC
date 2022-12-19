package framework.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import framework.runners.QueuedTaskRunner;
import framework.tasks.Task;
import framework.runners.TaskRunner;

/**
 * Implementation for a Fixed Size Thread Pool with a Queue of {@link Task} and a List of {@link TaskRunner} (Threads).
 * See {@link ThreadPool} for reference.
 * @author Lucas Damaceno
 */
public class FixedSizeThreadPool implements ThreadPool {
    private final BlockingQueue<Task> tasks;

    private final List<TaskRunner> runners;

    public FixedSizeThreadPool(int pool, int queueCapacity) {
        tasks = new ArrayBlockingQueue<>(queueCapacity);
        runners = new ArrayList<>();
        createRunners(pool);
    }

    public void submitTask(Task task) {
        tasks.add(task);
    }

    public void createRunners(int quantity) {
        for (int i = 0; i < quantity; i++) {
            TaskRunner taskExecutor = new QueuedTaskRunner(tasks);
            runners.add(taskExecutor);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    @Override
    public void close() throws Exception {
        boolean anyRunning;
        System.out.println("Invoked destroy method");

        do {

            runners.forEach(TaskRunner::tryStop);

            anyRunning = runners.stream()
                    .anyMatch(TaskRunner::isRunning);


        } while (anyRunning);

        System.out.println("Destroyed all threads");
    }
}
