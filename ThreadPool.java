import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool implements IThreadPool {
    BlockingQueue<Runnable> threadExecutors;
    List<ThreadTaskExecutor> threads;

    public ThreadPool(int pool, int queueCapacity){
        threadExecutors = new ArrayBlockingQueue<>(queueCapacity);
        threads = new ArrayList<>();
        createThreads(pool);
    }

    public void submitTask(Runnable runnable){
        int currQueuesize = threadExecutors.size();
        int currThreads = threads.size();
        if(currQueuesize > currThreads * 10){
            createThreads(currThreads);
        } else if(currQueuesize < currThreads * 10 && currThreads > 10) {
            removeThreads(currThreads / 2);
        }
        System.out.println("CurrExecutors: " + threads.size() + " CurrQueueSize: " + currQueuesize);
        threadExecutors.add(runnable);
    }

    private void createThreads(int quantityOfThreads) {
        for(int i = 0; i < quantityOfThreads; i++){
            ThreadTaskExecutor taskExecutor = new ThreadTaskExecutor(threadExecutors);
            threads.add(taskExecutor);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    private void removeThreads(int quantity){
        if(threads.size() > 10){
            threads = threads.subList(0, quantity);
        }
    }
}
