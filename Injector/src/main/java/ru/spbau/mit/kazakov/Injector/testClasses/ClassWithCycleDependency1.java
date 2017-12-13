package ru.spbau.mit.kazakov.Injector.testClasses;

import static org.junit.Assert.*;

public class ClassWithCycleDependency1 {
    public final ClassWithCycleDependency2 dependency;
    static int numberOfObjects = 0;

    public ClassWithCycleDependency1(ClassWithCycleDependency2 dependency) {
        this.dependency = dependency;
        numberOfObjects++;
        assertTrue(numberOfObjects <= 1);
    }
}
