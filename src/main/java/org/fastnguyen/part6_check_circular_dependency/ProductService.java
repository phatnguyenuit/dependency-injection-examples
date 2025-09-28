package org.fastnguyen.part6_check_circular_dependency;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:43
 * @project dependency-injection-examples
 */
public class ProductService {

  private final LoggerService logger;

  // ✅ Constructor Injection
  @Inject
  public ProductService(LoggerService logger) {
    this.logger = logger;
  }

  public void createProduct(String name) {
    logger.log("Created product: " + name);
  }
}
