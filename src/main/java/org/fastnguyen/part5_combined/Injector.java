package org.fastnguyen.part5_combined;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author PhatNguyen
 * @created 28/09/2025 - 15:43
 * @project dependency-injection-examples
 */
public class Injector {

  private final Map<Class<?>, Object> cache = new HashMap<>();
  private final Set<Class<?>> creationStack = new HashSet<>();

  // Public entrypoint
  public <T> T getInstance(Class<T> clazz) {
    if (cache.containsKey(clazz)) {
      return clazz.cast(cache.get(clazz));
    }

    T instance = createInstance(clazz);
    cache.put(clazz, instance);

    return instance;
  }

  // Core object creation
  private <T> T createInstance(Class<T> clazz) {
    try {
      // 1️⃣ Constructor Injection (prefer @Inject constructor)
      Constructor<?> injectCtor = Arrays.stream(clazz.getDeclaredConstructors())
          .filter(c -> c.isAnnotationPresent(Inject.class))
          .findFirst()
          .orElse(null);

      T obj;
      if (injectCtor != null) {
        Object[] params = Arrays.stream(injectCtor.getParameterTypes())
            .map(this::getInstance)
            .toArray();
        injectCtor.setAccessible(true);
        obj = (T) injectCtor.newInstance(params);
      } else {
        obj = clazz.getDeclaredConstructor().newInstance();
      }

      // 2️⃣ Field Injection
      for (Field f : clazz.getDeclaredFields()) {
        if (f.isAnnotationPresent(Inject.class)) {
          Object dep = getInstance(f.getType());
          f.setAccessible(true);
          f.set(obj, dep);
        }
      }

      // 3️⃣ Setter Injection (methods starting with "set")
      for (Method m : clazz.getDeclaredMethods()) {
        if (m.getName().startsWith("set") && m.getParameterCount() == 1) {
          Object dep = getInstance(m.getParameterTypes()[0]);
          m.setAccessible(true);
          m.invoke(obj, dep);
        }
      }

      // 4️⃣ Method Injection (@Inject on methods)
      for (Method m : clazz.getDeclaredMethods()) {
        if (m.isAnnotationPresent(Inject.class)) {
          Object[] params = Arrays.stream(m.getParameterTypes()).map(this::getInstance).toArray();
          m.setAccessible(true);
          m.invoke(obj, params);
        }
      }

      return obj;

    } catch (Exception e) {
      throw new RuntimeException("Failed to create " + clazz.getName(), e);
    }
  }
}
