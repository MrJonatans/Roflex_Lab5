package org.reflex;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Injector class to perform dependency injection on objects.
 */
public class Injector {
    private Config config;

    /**
     * Initializes the injector with a configuration file.
     *
     * @param configFilePath the path to the properties file
     * @throws IOException if the configuration file cannot be loaded
     */
    public Injector(String configFilePath) throws IOException {
        config = new Config(configFilePath);
    }

    /**
     * Injects dependencies into the fields of the given object.
     *
     * @param object the object to perform dependency injection on
     * @param <T>    the type of the object
     * @return the same object with dependencies injected
     * @throws Exception if the injection process fails
     */
    public <T> T inject(T object) throws Exception {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = config.getImplementationClass(fieldType.getName());

                if (implementationClassName != null) {
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();

                    field.setAccessible(true);
                    field.set(object, implementationInstance);
                } else {
                    throw new RuntimeException("No implementation found for " + fieldType.getName());
                }
            }
        }

        return object;
    }
}

