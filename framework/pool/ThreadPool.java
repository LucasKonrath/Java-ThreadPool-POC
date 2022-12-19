package framework.pool;
import framework.tasks.Task;
/**
 * Interface defining what is needed for a thread.pool.ThreadPool.
 * See also {@link FixedSizeThreadPool}.
 */
public interface ThreadPool extends AutoCloseable {

    void submitTask(Task task);

    void createRunners(int quantity);
}
