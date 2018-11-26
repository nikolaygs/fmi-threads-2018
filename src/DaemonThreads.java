import java.util.Set;

/**
 *  Deamon threads sample
 */
public class DaemonThreads {
  public static void main(String[] args) {
    BackgroundTask backgroundTask = new BackgroundTask();
    backgroundTask.setDaemon(true);
    backgroundTask.start();

    System.out.println("Main thread terminates");
  }

}
/**
 * The thread will count to 1000 in background.
 */
class BackgroundTask extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 1_000; i++) {
      System.out.println(i);
    }
    System.out.println("Deamon thread terminates");
  }
}

// Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
// for (Thread t : threadSet) {
// System.out.println(t.getName());
// }