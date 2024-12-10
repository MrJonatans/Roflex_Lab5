package org.reflex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration class to load interface-to-implementation mappings from a properties file.
 */
public class Config {
    private Properties properties = new Properties();

    /**
     * Loads configuration from the specified file located in the resources directory.
     *
     * @param configFileName the name of the properties file (e.g., "config.properties")
     * @throws IOException if the file cannot be loaded
     */
    public Config(String configFileName) throws IOException {
        // Получаем поток для чтения файла из ресурсов
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

        if (inputStream == null) {
            throw new IOException("Property file '" + configFileName + "' not found in the classpath.");
        }

        properties.load(inputStream);
    }

    /**
     * Retrieves the implementation class name for the given interface name.
     *
     * @param interfaceName the fully qualified name of the interface
     * @return the fully qualified name of the implementation class, or null if not found
     */
    public String getImplementationClass(String interfaceName) {
        return properties.getProperty(interfaceName);
    }
}
