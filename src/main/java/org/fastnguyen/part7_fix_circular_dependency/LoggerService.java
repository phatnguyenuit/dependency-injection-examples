package org.fastnguyen.part7_fix_circular_dependency;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:42
 * @project dependency-injection-examples
 */
public class LoggerService {

  void log(String msg) {
    System.out.println("[LOG] " + msg);
  }
}

