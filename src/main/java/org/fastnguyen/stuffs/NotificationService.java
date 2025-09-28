package org.fastnguyen.stuffs;

/**
 * @author PhatNguyen
 * @created 27/09/2025 - 14:02
 * @project dependency-injection-examples
 */
public class NotificationService {
  private final MessageService messageService;

  // Constructor injection
  public NotificationService(MessageService messageService) {
    this.messageService = messageService;
  }

  public void sendNotification(String notification) {
    messageService.sendMessage("Notification: " + notification);
  }
}