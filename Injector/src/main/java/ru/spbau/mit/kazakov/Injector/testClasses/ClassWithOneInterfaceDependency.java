package ru.spbau.mit.kazakov.Injector.testClasses;

import static org.junit.Assert.assertTrue;

public class ClassWithOneInterfaceDependency {
    public final Interface dependency;
    static int numberOfObjects = 0;

    public ClassWithOneInterfaceDependency(Interface dependency) {
        this.dependency = dependency;
        numberOfObjects++;
        assertTrue(numberOfObjects <= 1);
    }
}
