package ru.spbau.mit.kazakov.Injector.testClasses;

import static org.junit.Assert.assertTrue;

public class ClassWithCycleDependency2 {
    public final ClassWithCycleDependency3 dependency;
    static int numberOfObjects = 0;

    public ClassWithCycleDependency2(ClassWithCycleDependency3 dependency) {
        this.dependency = dependency;
        numberOfObjects++;
        assertTrue(numberOfObjects <= 1);
    }
}
