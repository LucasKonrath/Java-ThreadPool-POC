public interface Task<T> {
     T run() throws InterruptedException;
}
