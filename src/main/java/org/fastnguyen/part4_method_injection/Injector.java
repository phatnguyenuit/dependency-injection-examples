package org.fastnguyen.part4_method_injection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

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

      // find methods with @Inject
      for (Method m : clazz.getDeclaredMethods()) {
        if (m.isAnnotationPresent(Inject.class)) {
          Object[] params = Arrays.stream(m.getParameterTypes())
              .map(Injector::createInstance)
              .toArray();

          m.setAccessible(true);
          m.invoke(obj, params);
        }
      }

      return obj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
