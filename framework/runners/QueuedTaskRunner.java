package framework.runners;

import framework.tasks.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueuedTaskRunner implements TaskRunner {

    private final BlockingQueue<Task> queue;

    private final AtomicBoolean running;

    private final AtomicBoolean executing;

    public QueuedTaskRunner(BlockingQueue<Task> queue){
        this.queue = queue;
        executing = new AtomicBoolean(false);
        running = new AtomicBoolean(false);
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
    public Task getTask() {
        return queue.poll();
    }

    @Override
    public void run() {
        running.set(true);
        do {
            try {
                Task task = getTask();
                if(task != null){
                    executing.set(true);
                    task.run();
                    executing.set(false);
                    System.out.println("Remaining tasks: " + queue.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(running.get());
    }
}
