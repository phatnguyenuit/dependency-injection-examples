package org.fastnguyen.stuffs;

/**
 * @author PhatNguyen
 * @created 27/09/2025 - 14:07
 * @project dependency-injection-examples
 */
public class Example {

  public static void main(String[] args) {
    DependencyContainer container = new DependencyContainer();

    // Register the service implementation
    container.register(MessageService.class, EmailService.class);

    // Register a primitive value
    container.register(int.class, 123);

    // Get the client instance, and its dependencies will be injected
    NotificationService notificationService = container.getInstance(NotificationService.class);
    notificationService.sendNotification("Welcome to our application!");

    // Get an instance of MyService, the int dependency will be injected
    MyService myService = container.getInstance(MyService.class);
    myService.printValue(); // This will print "The injected value is: 123"
  }

}
