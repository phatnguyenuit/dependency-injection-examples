package org.fastnguyen.part4_method_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:44
 * @project dependency-injection-examples
 */
public class AppPart4 {

  public static void main(String[] args) {
    AppService as = Injector.createInstance(AppService.class);
    as.run();
  }
}
