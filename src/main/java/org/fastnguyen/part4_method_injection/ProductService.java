package org.fastnguyen.part4_method_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:43
 * @project dependency-injection-examples
 */
public class ProductService {

  @Inject
  private LoggerService logger;  // Field injection

  public void createProduct(String name) {
    logger.log("Created product: " + name);
  }
}
