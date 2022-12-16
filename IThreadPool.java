public interface IThreadPool extends AutoCloseable {

    void submitTask(Task task);

}
