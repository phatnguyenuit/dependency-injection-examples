package org.fastnguyen.part5_combined;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 16:02
 * @project dependency-injection-examples
 */
public class AppService {

  // ✅ Field Injection
  @Inject
  private ConfigService config;

  private ProductService productService;

  // ✅ Setter Injection
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  // ✅ Method Injection
  @Inject
  public void init(LoggerService logger) {
    logger.log("AppService initialized with config: " + config.getConfig());
  }

  public void run() {
    productService.createProduct("Laptop");
  }
}
