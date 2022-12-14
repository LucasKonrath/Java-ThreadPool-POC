public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //create queue size - 200
        //Number of threads - 4
        ThreadPool threadPool = new ThreadPool(4,200);

        for(int taskNumber = 1 ; taskNumber <= 7; taskNumber++) {
            Task task = new Task(500l);
            threadPool.addTask(task);
        }
        System.out.println("Stop here to test");

    }
}