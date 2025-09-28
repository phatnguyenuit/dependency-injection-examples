package org.fastnguyen.part5_combined;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:44
 * @project dependency-injection-examples
 */
public class AppPart5 {

  public static void main(String[] args) {
    Injector injector = new Injector();

    AppService app = injector.getInstance(AppService.class);
    app.run();
  }
}
