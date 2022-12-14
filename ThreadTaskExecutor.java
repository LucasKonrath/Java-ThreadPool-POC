import java.util.concurrent.BlockingQueue;

public class ThreadTaskExecutor implements Runnable {

    BlockingQueue<Runnable> queue;

    public ThreadTaskExecutor(BlockingQueue<Runnable> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        do {
            try {
                Runnable task = queue.poll();
                if(task != null){
                    task.run();
                    System.out.println("Remaining tasks: " + queue.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
