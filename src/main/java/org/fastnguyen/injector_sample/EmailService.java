package org.fastnguyen.injector_sample;

/**
 * @author PhatNguyen
 * @created 27/09/2025 - 14:02
 * @project dependency-injection-examples
 */
public class EmailService implements MessageService{

  @Override
  public void sendMessage(String message) {
    System.out.println("Email: " + message);
  }
}
