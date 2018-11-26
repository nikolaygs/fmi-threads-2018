package bank;

public class BankAccount {

  // You can test removing static synchronization and use AtomicInteger
  private static int opCount = 0;

  // Holder's name
  protected String name;

  // Balance
  protected double balance = 0;

  public BankAccount(String name, double balance) {
    this.name = name;
    this.balance = balance;
  }

  public BankAccount(String name) {
    this.name = name;
  }

  public void deposit(double amount) {
    this.balance += amount;
    incrementOpCount();
  }

  public void withdraw(double amount) {
    this.balance -= amount;
    incrementOpCount();
  }

  public String getName() {
    return name;
  }

  public static void incrementOpCount() {
    opCount++;
  }

  public static int getOpCount() {
    return opCount;
  }

  @Override
  public String toString() {
    return name + " balance is " + balance;
  }
}