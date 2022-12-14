import java.util.UUID;

public class Task implements Runnable {

    long sleepTime;
    UUID uuid;

    public Task(long sleepTime){
        this.sleepTime = sleepTime;
        uuid = UUID.randomUUID();
    }
    @Override
    public void run() {
        System.out.println("Starting task");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finishing task -  " + uuid);
    }
}
