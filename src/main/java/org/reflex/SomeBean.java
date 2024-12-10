package org.reflex;

/**
 * A sample bean class with fields to be injected.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Calls methods on the injected dependencies.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}
