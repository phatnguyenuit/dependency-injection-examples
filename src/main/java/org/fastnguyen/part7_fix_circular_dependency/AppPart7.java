package org.fastnguyen.part7_fix_circular_dependency;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:44
 * @project dependency-injection-examples
 */
public class AppPart7 {

  public static void main(String[] args) {
    Injector injector = new Injector();

    try {
      ServiceA serviceA = injector.getInstance(ServiceA.class);
      ServiceB serviceB = injector.getInstance(ServiceB.class);

      serviceA.hello();
      serviceB.hello();
    } catch (Exception e) {
      System.out.println("[ERROR] " + e.getMessage());
    }
  }
}

// Example of circular dependencies
class ServiceA {
  @Inject
  private ServiceB serviceB;

  void hello() { System.out.println("Hello from A, got B: " + serviceB); }
}

class ServiceB {
  @Inject
  private ServiceA serviceA;

  void hello() { System.out.println("Hello from B, got A: " + serviceA); }
}