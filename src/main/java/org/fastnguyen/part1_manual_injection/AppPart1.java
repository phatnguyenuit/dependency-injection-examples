package org.fastnguyen.part1_manual_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:22
 * @project dependency-injection-examples
 */
public class AppPart1 {

  public static void main(String[] args) {
    EmailService emailService = new EmailService();   // create dependency
    UserService userService = new UserService(emailService); // inject manually
    userService.registerUser("Alice");
  }
}
