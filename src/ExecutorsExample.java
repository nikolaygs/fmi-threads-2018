
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Demo of different exexutor implementations @see ExecutorService
 */
public class ExecutorsExample {
  private static final int EXEC_COUNT = 5;

  public static void main(String[] args) throws Exception {
    // Uncomment different executor pools to check how they work - 
    // in the output pay attention of the thread name used to execute the task.
    // Pool with a single threads
    testExecutor(Executors.newSingleThreadExecutor());
    System.out.println("===========================");

    // Pool with exactly 3 threads
    testExecutor(Executors.newFixedThreadPool(3));
    System.out.println("===========================");

    // Pool with self-adjusting size
    testExecutor(Executors.newCachedThreadPool());
    System.out.println("===========================");

//     Pool for scheduled execution
    testExecutor(Executors.newScheduledThreadPool(1));
  }

  private static void testExecutor(ExecutorService executor) throws Exception {
    for (int i = 0; i < EXEC_COUNT; i++) {
      final int taskId = i;
      executor.execute(new Runnable() { // Executes asynchronously!
        @Override
        public void run() {
          System.out.println("Task " + taskId + " was executed by " + Thread.currentThread().getName());
        }
      });
    }

    // wait all threads to finish
    Thread.sleep(2000);

    // we must always turn executor off
    executor.shutdown();
  }

  private static void testExecutor(ScheduledExecutorService executor) throws Exception {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        System.out.println("Scheduled task execution by: " + Thread.currentThread().getName());
      }
    };

    // Submit one task that is going to be executed 10 times each 2 seconds 
    executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS); // Executes asynchronously!

    // wait 10 executions
    Thread.sleep(10000);

    // Shut down the pool
    executor.shutdown();
  }
}
