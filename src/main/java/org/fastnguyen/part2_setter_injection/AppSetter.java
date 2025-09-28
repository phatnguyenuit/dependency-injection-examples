package org.fastnguyen.part2_setter_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:35
 * @project dependency-injection-examples
 */
public class AppSetter {

  public static void main(String[] args) {
    PaymentService ps = new PaymentService();
    OrderService os = new OrderService();

    os.setPaymentService(ps);  // inject after construction
    os.placeOrder("Book", 20.0);
  }
}
