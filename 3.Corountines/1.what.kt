Funtions:
A function is a sequence of instructions that takes inputs and gives us outputs.

Threads:
A thread describes in which context this sequence of instructions should be executed.

Thread: 1

println("Hello World")
var x = 3
x *= x
println("The Result is $x")

Thread: 2

println("Hello World from the 2. Thread!")
var x = 3
x *= x
println("The result from 2. Thread is $x")

Both thread starts to run in parallel
Each thread will execute its own instructions in a world
But cannot predict in which order all instructions in total are executed.
For Example: 
Thread 1 finished line 4 while Thread 2 still in line 2

Why is threading Important

In android every instructions are generally executed in a single thread 
which is called main thread.

Everything runs in single thread it will take time to execute multiple
instructions.

Then comes Multithreading to solve the problem.
Network Calls, Database Operations, Complex Calculations

Coroutines just has Threads it will execute the instructions inside a thread
1. Can Execute multiple coroutine inside a single thread.
2. Coroutines are suspendable -> pause the execution in the middle and let it continue when wanted
3. They can switch their context -> Coroutines that started in one thread can easily switch the thread is running in

Coroutines -> Its a lightweight thread with extra functionality