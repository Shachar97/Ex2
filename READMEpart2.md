# Ex2
## matala02 part 1 Ex2_1 readMe:
### Task
The Task class implement from Callable and Comparable interfaces and extern from FutureTask.
task member is like FutureTask member with the different that we can compare between tasks by their priority's.
so in the field of Task, we have TaskType for the priority,a Callable member type for the call func, and a Generic member, so the task could be from different types.
### CustomExecutor
the CustomExecutor class is an executor for the Task class, so he has a priority blocking queue. and executor-pool for the threads, in this case ThreadPoolExecutor type.
