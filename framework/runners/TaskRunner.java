package framework.runners;

import framework.tasks.Task;

/**
 * Definition of what is needed for a TaskRunner.
 * Please see {@link framework.tasks.Task} for more information.
 */
public interface TaskRunner extends Runnable {

    /**
     * Tries to stop and kill the current thread.
     * @return Returns if the thread was successfully removed.
     */
    boolean tryStop();

    /**
     * @return Returns if the current thread is running.
     */
    boolean isRunning();

    /**
     * @return The current Task to execute.
     */
    Task getTask();
}
