package ru.spbau.mit.kazakov.Injector.testClasses;

import static org.junit.Assert.assertTrue;

public class ClassWithOneClassDependency {
    public final ClassWithoutDependencies dependency;
    static int numberOfObjects = 0;

    public ClassWithOneClassDependency(ClassWithoutDependencies dependency) {
        this.dependency = dependency;
        numberOfObjects++;
        assertTrue(numberOfObjects <= 1);
    }
}
