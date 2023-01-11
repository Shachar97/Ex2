# Ex2
## part 1 Ex2_1 :
 In the first function we created text files. Then we write in each one of them random number of lines.
 
In the 2 function we calculate the number of lines in all the files together by loop in loop,
we go throw all files and, in each file, we sum the lines.

In the 3 function we calculate the number of lines in different method from the first one.
We created class that extends from thread, the constructor get String file's name and the run() function sum the lines in the file and put
the counter in class parameter num.
Than we created this class and start the run() function for each file, so that the calculation of each file
will happen in concurrently and by that we will save running time.

In the 4 function we calculate the number of lines in different method from the others. 
We created a class that implement Callable that called callableNumOfLines . 
Call  calculate the number of lines in the file and return in.
than in a function out of the class we created a thread pool and array of callableNumOfLines 
, for each file we created a callableNumOfLines  and add it to the array. 
Than we insert it in to the thread pool and start all the call() function by using one of the thread pool function.

We run all the methods for 10000 files the the worst method is the first one, 
it took her 14.4 sec, second is the last method it took her 5.5 sec and the best method is the second method it took her 1.4 sec.
the first method is the worst because it waits to the end of calculation of file before moving to the next
but the others methods do the calculations of the files in concurrently and because of that they are more faster. 
We can conclude from this project that when we have multi tasks that we need to perform,
and they don’t dependent on each other we should use in threads(and threads pool) for shorten running time.    

## matala02 part 2:
### Task
The Task class implement from Callable and Comparable interfaces and extern from FutureTask.
task member is like FutureTask member with the different that we can compare between tasks by their priority's.
so in the field of Task, we have TaskType for the priority,a Callable member type for the call func, and a Generic member, so the task could be from different types.
### CustomExecutor
the CustomExecutor class is an executor for the Task class, so he has a priority blocking queue. and executor-pool for the threads, in this case ThreadPoolExecutor type.
