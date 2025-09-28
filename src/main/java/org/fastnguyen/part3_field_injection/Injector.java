package org.fastnguyen.part3_field_injection;

import java.lang.reflect.Field;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:43
 * @project dependency-injection-examples
 */
public class Injector {

  public static <T> T createInstance(Class<T> clazz) {
    try {
      T obj = clazz.getDeclaredConstructor().newInstance();

      // find fields with @Inject
      for (Field f : clazz.getDeclaredFields()) {
        if (f.isAnnotationPresent(Inject.class)) {
          Class<?> depType = f.getType();
          Object dep = createInstance(depType);  // recursive
          f.setAccessible(true);
          f.set(obj, dep);
        }
      }
      return obj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
