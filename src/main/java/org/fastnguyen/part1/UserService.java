package org.fastnguyen.part1;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:21
 * @project dependency-injection-examples
 */
public class UserService {

  private final EmailService emailService;

  // Constructor Injection
  public UserService(EmailService emailService) {
    this.emailService = emailService;
  }

  public void registerUser(String name) {
    System.out.println("Registering " + name);
    emailService.sendEmail("Welcome " + name + "!");
  }
}
