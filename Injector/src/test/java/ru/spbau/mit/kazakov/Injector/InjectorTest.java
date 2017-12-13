package ru.spbau.mit.kazakov.Injector;

import org.junit.Test;
import ru.spbau.mit.kazakov.Injector.testClasses.ClassWithOneClassDependency;
import ru.spbau.mit.kazakov.Injector.testClasses.ClassWithOneInterfaceDependency;
import ru.spbau.mit.kazakov.Injector.testClasses.ClassWithoutDependencies;
import ru.spbau.mit.kazakov.Injector.testClasses.InterfaceImpl;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class InjectorTest {
    private static final String TEST_CLASS_PREFIX = "ru.spbau.mit.kazakov.Injector.testClasses.";

    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize(TEST_CLASS_PREFIX + "ClassWithoutDependencies",
                Collections.emptyList());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                TEST_CLASS_PREFIX + "ClassWithOneClassDependency",
                Collections.singletonList(TEST_CLASS_PREFIX + "ClassWithoutDependencies")
        );
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                TEST_CLASS_PREFIX + "ClassWithOneInterfaceDependency",
                Collections.singletonList(TEST_CLASS_PREFIX + "InterfaceImpl")
        );
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }

    @Test(expected = AmbiguousImplementationException.class)
    public void injectorThrowsAmbiguousImplementationsException() throws Exception {
        Object object = Injector.initialize(TEST_CLASS_PREFIX + "ClassWithOneInterfaceDependency",
                Arrays.asList(TEST_CLASS_PREFIX + "InterfaceImpl", TEST_CLASS_PREFIX + "InterfaceImpl"));
    }

    @Test(expected = ImplementationNotFoundException.class)
    public void injectorThrowsImplementationNotFoundException() throws Exception {
        Object object = Injector.initialize(TEST_CLASS_PREFIX + "ClassWithOneInterfaceDependency",
                Collections.singletonList(TEST_CLASS_PREFIX + "ClassWithoutDependencies"));
    }

    @Test(expected = InjectionCycleException.class)
    public void injectorThrowsInjectionCycleException() throws Exception {
        Object object = Injector.initialize(TEST_CLASS_PREFIX + "ClassWithCycleDependency1",
                Arrays.asList(TEST_CLASS_PREFIX + "ClassWithCycleDependency1",
                        TEST_CLASS_PREFIX + "ClassWithCycleDependency2", TEST_CLASS_PREFIX + "ClassWithCycleDependency3"));
    }
}