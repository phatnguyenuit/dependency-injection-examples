package org.fastnguyen.part6_check_circular_dependency;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:44
 * @project dependency-injection-examples
 */
public class AppPart6 {

  public static void main(String[] args) {
    Injector injector = new Injector();

    try {
      injector.getInstance(ServiceA.class);
    } catch (Exception e) {
      System.out.println("[ERROR] " + e.getMessage());
    }
  }
}

// Example of circular dependencies
class ServiceA {
  @Inject
  private ServiceB serviceB;
}

class ServiceB {
  @Inject
  private ServiceA serviceA;
}