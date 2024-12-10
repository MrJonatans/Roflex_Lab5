package org.reflex;

/**
 * Implementation of SomeInterface that performs action B.
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
