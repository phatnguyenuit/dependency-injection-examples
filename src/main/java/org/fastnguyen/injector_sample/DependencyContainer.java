package org.fastnguyen.injector_sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author PhatNguyen
 * @created 27/09/2025 - 14:04
 * @project dependency-injection-examples
 */
public class DependencyContainer {

  private final Map<Class<?>, Object> instances = new HashMap<>();
  private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new HashMap<>();

  static {
    PRIMITIVE_WRAPPER_MAP.put(boolean.class, Boolean.class);
    PRIMITIVE_WRAPPER_MAP.put(byte.class, Byte.class);
    PRIMITIVE_WRAPPER_MAP.put(char.class, Character.class);
    PRIMITIVE_WRAPPER_MAP.put(short.class, Short.class);
    PRIMITIVE_WRAPPER_MAP.put(int.class, Integer.class);
    PRIMITIVE_WRAPPER_MAP.put(long.class, Long.class);
    PRIMITIVE_WRAPPER_MAP.put(float.class, Float.class);
    PRIMITIVE_WRAPPER_MAP.put(double.class, Double.class);
  }

  // Register a service implementation
  public <T> void register(Class<T> interfaceType, Class<? extends T> implementationType) {
    try {
      // Create an instance of the implementation and store it
      instances.put(interfaceType, implementationType.getDeclaredConstructor().newInstance());
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
      throw new RuntimeException(
          "Error registering dependency: " + implementationType.getName(),
          e
      );
    }
  }

  // Register a specific instance for a type
  public <T> void register(Class<T> type, T instance) {
    instances.put(type, instance);
  }

  // Get an instance of a dependency
  @SuppressWarnings("unchecked")
  public <T> T getInstance(Class<T> type) {
    // Handle registered instances (including primitives registered with their class)
    if (instances.containsKey(type)) {
      return (T) instances.get(type);
    }

    // For primitives, check if a wrapper instance is registered
    if (type.isPrimitive()) {
      Class<?> wrapperType = PRIMITIVE_WRAPPER_MAP.get(type);
      if (wrapperType != null && instances.containsKey(wrapperType)) {
        return (T) instances.get(wrapperType);
      }
    }

    // If not explicitly registered, try to create an instance via constructor injection
    try {
      Constructor<?>[] constructors = type.getDeclaredConstructors();
      if (constructors.length == 0) {
        throw new InstantiationException("No constructor found for " + type.getName());
      }
      // Assuming the first constructor is the one to use.
      Constructor<T> constructor = (Constructor<T>) constructors[0];

      Object[] params = new Object[constructor.getParameterCount()];
      for (int i = 0; i < constructor.getParameterCount(); i++) {
        params[i] = getInstance(constructor.getParameterTypes()[i]);
      }
      T newInstance = constructor.newInstance(params);
      instances.put(type, newInstance); // Cache for future use
      return newInstance;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Error creating instance for: " + type.getName(), e);
    }
  }
}
