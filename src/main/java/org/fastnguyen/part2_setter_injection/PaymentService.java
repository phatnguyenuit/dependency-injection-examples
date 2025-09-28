package org.fastnguyen.part2_setter_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:34
 * @project dependency-injection-examples
 */
public class PaymentService {

  public void pay(double amount) {
    System.out.println("Paid: $" + amount);
  }
}
