package part2;

import java.util.concurrent.*;

public class Task<V> extends FutureTask<V> implements Callable<V>, Comparable<Task<V>>{
    //FIELDS:
    private V result; //for holding the value of return
    private Callable<V> task;
    private TaskType priority;

    //CONSTRUCTORS:

    /**
     * extend from FutureTask constructor and add priority to the task
     * this Constructor will build only task that is not null
     * @param task a callable type
     * @throws NullPointerException
     */
    private Task(Callable<V> task){
            super(task);
            this.task = task;
            this.priority= TaskType.OTHER; //my default
    }


    /**
     * extend from FutureTask constructor and add priority to the task
     * this Constructor will build only task that is not null
     * @param task a callable type
     * @param priority between 1-10
     * @throws NullPointerException
     */
    private Task(Callable<V> task, TaskType priority){
        super(task);
        this.task = task;
        if (priority == null){
            this.priority= TaskType.OTHER; //my default
        }else{
            this.priority = priority.getType();
        }
    }

    //FACTORY:

    /**
     * the way to build Task is only with Factorial: 'createTask' methods
     * @param task a Callable  Dynamic type
     * @param priority between 1-10
     * @return Task type
     * @param <V> the value
     */
    public static <V> Task createTask(Callable<V> task, TaskType priority){//check errors
        return new Task(task,priority);
    }

    /**
     * the way to build Task is only with Factorial: 'createTask' methods
     * @param task a Callable  Dynamic type
     * @return a Task type
     * @param <V> the value
     */
    public static <V> Task createTask(Callable<V> task){
        return new Task(task);
    }

    //GETTERS & SETTERS
    public Callable<V> getTask() {
        return this.task;
    }

    public void setTask(Callable<V> task) {
        this.task = task;
    }

    public TaskType getPriority() {
        return this.priority;
    }

    public void setPriority(TaskType priority) {
        this.priority = priority;
    }

    /**
     *  compare by priority type value
     * @param otherTask the object to be compared whith.
     * @return int positive-negative or zero to compare between the two Tasks
     */
    @Override
    public int compareTo(Task otherTask) {

        return Integer.compare(this.priority.getPriorityValue(),otherTask.priority.getPriorityValue());
    }

    /**
     * this function activate the task
     */
    @Override
    public V call() throws Exception {
        if (this.task != null){
            return  (V) this.task.call();
        }
        return null;
    }

    public String tString(){
        return "Task:\n\t return: " + result + ",\n\t type: " + priority.getType() + ",\n\t priority: " + priority.getPriorityValue() +".";
    }
}
