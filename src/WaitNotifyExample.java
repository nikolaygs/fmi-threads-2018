
import bank.BankAccount;

public class WaitNotifyExample {
  public static void main(String[] args) throws Exception {
    CreditBankAccount account = new CreditBankAccount("Student");

    // The thread will try to withdraw 100
    CreditFeeTaker creditFeeTaker = new CreditFeeTaker(account, 100);
    creditFeeTaker.setName("First fee");
    creditFeeTaker.start();

    // The thread will try to withdraw 50
    CreditFeeTaker creditFeeTaker2 = new CreditFeeTaker(account, 50);
    creditFeeTaker2.setName("Second fee");
    creditFeeTaker2.start();

    // Wait a bit
    Thread.sleep(2500);

    // Deposit 120
    account.deposit(120);

    // Wait a bit
    Thread.sleep(2500);

    // Deposit more money
    account.deposit(30);

    creditFeeTaker.join();
    creditFeeTaker2.join();
    System.out.println(account);
  }
}

class CreditBankAccount extends BankAccount {
  public CreditBankAccount(String name) {
    super(name, 0);
  }

  @Override
  public void deposit(double amount) {
    super.deposit(amount);
  }

  public void withdrawCreditPayment(double monthFee) {
    while (balance < monthFee) {
      try {
        System.out.println("Not enought money ... waiting");
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    withdraw(monthFee);
    System.out.println("Successully withdraw money. Remaining balance " + balance);
  }
}

class CreditFeeTaker extends Thread {
  private CreditBankAccount account;
  private double fee;

  public CreditFeeTaker(CreditBankAccount account, double fee) {
    this.account = account;
    this.fee = fee;
  }

  @Override
  public void run() {
    account.withdrawCreditPayment(this.fee);
    System.out.println(this.getName() + " completed");
  }
}