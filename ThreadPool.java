import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    BlockingQueue<Task> threadExecutors;

    public ThreadPool(int pool, int queueCapacity){
        threadExecutors = new ArrayBlockingQueue<>(queueCapacity);
        for(int i = 0; i < pool; i++){
            ThreadTaskExecutor taskExecutor = new ThreadTaskExecutor(threadExecutors);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    public void addTask(Task task){
        threadExecutors.add(task);
    }
}
