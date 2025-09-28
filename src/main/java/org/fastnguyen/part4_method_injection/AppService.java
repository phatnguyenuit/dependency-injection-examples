package org.fastnguyen.part4_method_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 16:02
 * @project dependency-injection-examples
 */
public class AppService {

  private ConfigService config;

  // Method Injection (called by container after construction)
  @Inject
  public void configure(ConfigService config) {
    System.out.println("Called configure() method with parameter: " + config);
    this.config = config;
  }

  public void run() {
    System.out.println("App running with config: " + config.getConfig());
  }
}
