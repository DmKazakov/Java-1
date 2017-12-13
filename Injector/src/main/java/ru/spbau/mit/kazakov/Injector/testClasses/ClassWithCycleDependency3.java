package ru.spbau.mit.kazakov.Injector.testClasses;

import static org.junit.Assert.assertTrue;

public class ClassWithCycleDependency3 {
    public final ClassWithCycleDependency1 dependency;
    static int numberOfObjects = 0;

    public ClassWithCycleDependency3(ClassWithCycleDependency1 dependency) {
        this.dependency = dependency;
        numberOfObjects++;
        assertTrue(numberOfObjects <= 1);
    }
}
