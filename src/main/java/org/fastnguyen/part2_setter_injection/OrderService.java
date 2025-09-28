package org.fastnguyen.part2_setter_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:35
 * @project dependency-injection-examples
 */
public class OrderService {

  private PaymentService paymentService;

  // Setter Injection
  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public void placeOrder(String item, double amount) {
    System.out.println("Order placed: " + item);
    paymentService.pay(amount);
  }
}
