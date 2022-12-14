import java.util.concurrent.BlockingQueue;

public class ThreadTaskExecutor implements Runnable {

    BlockingQueue<Task> queue;

    public ThreadTaskExecutor(BlockingQueue<Task> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        do {
            try {
                Task task = queue.poll();
                if(task != null)
                    task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
