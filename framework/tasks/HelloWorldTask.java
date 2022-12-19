package framework.tasks;

import framework.tasks.Task;

public class HelloWorldTask implements Task<String> {
    @Override
    public String run() throws InterruptedException {
        return "Hello World";
    }
}
