package org.reflex;

public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = new Injector("config.properties");
        SomeBean sb = injector.inject(new SomeBean());
        sb.foo(); // Outputs "AC" or "BC" based on configuration
    }
}
