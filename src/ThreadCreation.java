
public class ThreadCreation {

  public static void main(String[] args) {
    System.out.println("Hello from main");
  }
}

class CustomThread extends Thread {
  public void run() {
    System.out.println("Hello async");
  }
}
