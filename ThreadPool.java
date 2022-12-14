import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    BlockingQueue<Runnable> threadExecutors;
    List<ThreadTaskExecutor> threads;

    public ThreadPool(int pool, int queueCapacity){
        threadExecutors = new ArrayBlockingQueue<>(queueCapacity);
        threads = new ArrayList<>();
        for(int i = 0; i < pool; i++){
            ThreadTaskExecutor taskExecutor = new ThreadTaskExecutor(threadExecutors);
            threads.add(taskExecutor);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    public void submitTask(Runnable runnable){
        int currQueuesize = threadExecutors.size();
        if(currQueuesize > threads.size() * 10){
            doubleThreads();
        } else if(currQueuesize < threads.size() * 10)  {
            halveThreads();
        }
        System.out.println("CurrExecutors: " + threads.size() + " CurrQueueSize: " + currQueuesize);
        threadExecutors.add(runnable);
    }

    private void doubleThreads(){
        int currThreads = threads.size();
        for(int i = currThreads; i < currThreads * 2; i++){
            ThreadTaskExecutor taskExecutor = new ThreadTaskExecutor(threadExecutors);
            threads.add(taskExecutor);
            Thread thread = new Thread(taskExecutor);
            thread.start();
        }
    }

    private void halveThreads(){
        if(threads.size() > 10){
            threads = threads.subList(0, threads.size() / 2);
        }
    }
}
