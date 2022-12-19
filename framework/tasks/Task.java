package framework.tasks;

public interface Task<T> {
     T run() throws InterruptedException;
}
