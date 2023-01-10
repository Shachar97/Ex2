package part2;

import java.util.concurrent.*;

public class CustomExecutor {

    //FIELDS:
    private ThreadPoolExecutor executor;
    private PriorityBlockingQueue queue;
    private final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final int idleTime = 300; //in ms
    private static int highPriority;

    //CONSTRUCTOR

    /**
     * Default Constructor build Excutor program with:
     *      minimum half of valid processors,
     *      max with valid-1,
     *      keepAliveTime- 300 mls
     *      blockingQueue- Priority by TaskType default
     */
    public CustomExecutor(){
        this.queue = new PriorityBlockingQueue<Task>();
        this.executor = new ThreadPoolExecutor(
                (availableProcessors/2) , (availableProcessors-1) , idleTime,(TimeUnit.MILLISECONDS),  queue);
    }

    //SUBMITS:

    /**
     * this program enter to the queue the task with the priority, because the task are callable they will return future type
     * @param callableTask a callable task
     * @param priority from TaskType enum
     * @return return Future Type
     * @param <T> the type of the task
     */
    public  <T> Future submit(Callable callableTask, TaskType priority){

        if (callableTask != null){
            if (priority != null){
                Task task= Task.createTask(callableTask, priority);

                return submit(task);

            }else {
                Task task= Task.createTask(callableTask);

                return submit(task);
            }

        }
        throw new NullPointerException();
    }

    /**
     * this program enter to the queue the task with default priority, because the task are callable they will return future type
     * @param callableTask  a callable task
     * @return return Future Type
     * @param <T> the type of the task
     */
    public <T>Future submit(Callable<T> callableTask){

        if (callableTask != null ){
            Task<T> task = Task.createTask(callableTask);

            return submit(task);
        }
        throw new NullPointerException();
    }

    /**
     * this program enter to the queue the task, because the task are callable they will return future type
     * @param task task type with priority
     * @return return Future Type
     * @param <T> the type of the task
     */
    public <T> Future submit(Task<T> task){
        if (task != null ){
            this.executor.execute(task);
            this.highPriority = Math.min(this.highPriority,task.getPriority().getPriorityValue());
            return task;
        }
        throw new NullPointerException();
    }

    /**
     *
     * @return the current high priority in the queue in O(1) (not updated if the task already done)
     */
    public int getCurrentMax(){
        return this.highPriority;
    }

    /**
     * close the queue for new tasks, and wait for the queue to get empty
     */
    public void gracefullyTerminate(){
        this.executor.shutdown();
    }

    // GETTERS & SETTERS:
    public void setCorePoolSize(int i) {
        this.executor.setCorePoolSize(i);
    }

    public void setMaximumPoolSize(int i) {
        this.executor.setMaximumPoolSize(i);
    }

    public Object getQueueP() {
        return this.queue;
    }
}
