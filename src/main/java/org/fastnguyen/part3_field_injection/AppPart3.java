package org.fastnguyen.part3_field_injection;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:44
 * @project dependency-injection-examples
 */
public class AppPart3 {

  public static void main(String[] args) {
    ProductService ps = Injector.createInstance(ProductService.class);
    ps.createProduct("Laptop");
  }
}
