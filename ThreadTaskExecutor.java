import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadTaskExecutor implements Runnable {

    BlockingQueue<Task> queue;

    AtomicBoolean running = new AtomicBoolean(false);

    AtomicBoolean executing = new AtomicBoolean(false);

    public ThreadTaskExecutor(BlockingQueue<Task> queue){
        this.queue = queue;
    }

    public boolean tryStop(){
        if(!running.get()){
            return true;
        } else if(queue.size() == 0 && !executing.get()){
            running.set(false);
            return true;
        }
        return false;
    }

    public boolean isRunning(){
        return running.get();
    }

    @Override
    public void run() {
        running.set(true);
        do {
            try {
                Task task = queue.poll();
                if(task != null){
                    executing = new AtomicBoolean(true);
                    task.run();
                    executing = new AtomicBoolean(false);
                    System.out.println("Remaining tasks: " + queue.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(running.get());
    }
}
