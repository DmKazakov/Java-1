package ru.spbau.mit.kazakov.Injector;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * A class for creating an object of specified class using given classes.
 */
public class Injector {
    /**
     * Creates an object of specified class using classes from specified container.
     *
     * @param rootClassName   specified class
     * @param implementations for creating an object
     * @return created object
     * @throws AmbiguousImplementationException when found multiple implementations for a dependency
     * @throws ImplementationNotFoundException  if found no implementation for a dependency
     * @throws InjectionCycleException          if found a circular dependency
     * @throws ClassNotFoundException           if found not existing class
     */
    @NotNull
    public static Object initialize(@NotNull String rootClassName, @NotNull List<String> implementations) throws Exception {
        return create(rootClassName, implementations, new TreeSet<>(), new HashMap<>());
    }

    /**
     * Creates an object of specified class using classes from specified container.
     *
     * @param className       specified class
     * @param implementations for creating an object
     * @param demanded        already required dependencies
     * @param created         already created objects
     * @return created object
     * @throws AmbiguousImplementationException when found multiple implementations for a dependency
     * @throws ImplementationNotFoundException  if found no implementation for a dependency
     * @throws InjectionCycleException          if found a circular dependency
     * @throws ClassNotFoundException           if found not existing class
     */
    @NotNull
    private static Object create(@NotNull String className, @NotNull List<String> implementations,
                                 @NotNull Set<String> demanded,
                                 @NotNull Map<String, Object> created) throws Exception {
        if (demanded.contains(className)) {
            if (created.containsKey(className)) {
                return created.get(className);
            } else {
                throw new InjectionCycleException();
            }
        }
        demanded.add(className);

        Class<?> toCreate = Class.forName(className);
        if (Modifier.isAbstract(toCreate.getModifiers()) || toCreate.isInterface()) {
            String foundImplementation = null;
            for (String implementation : implementations) {
                Class<?> implementationClass = Class.forName(implementation);
                if (toCreate.isAssignableFrom(implementationClass) && !toCreate.equals(implementationClass)) {
                    if (foundImplementation != null) {
                        throw new AmbiguousImplementationException();
                    } else {
                        foundImplementation = implementation;
                    }
                }
            }
            if (foundImplementation == null) {
                throw new ImplementationNotFoundException();
            }
            Object implementationObject = create(foundImplementation, implementations, demanded, created);
            created.put(className, implementationObject);
            return implementationObject;
        }

        Constructor<?> constructor = toCreate.getConstructors()[0];
        Class<?>[] parameters = constructor.getParameterTypes();
        ArrayList<Object> createdParameters = new ArrayList<>();
        for (Class<?> parameter : parameters) {
            String parameterName = parameter.getCanonicalName();
            Class<?> paramClass = Class.forName(parameterName);
            if (!paramClass.isInterface() && !Modifier.isAbstract(paramClass.getModifiers())) {
                boolean found = false;
                for (String implementationName : implementations) {
                    if (parameterName.equals(implementationName)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new ImplementationNotFoundException();
                }
            }
            createdParameters.add(create(parameterName, implementations,
                    demanded, created));
        }
        Object classInstance = constructor.newInstance(createdParameters.toArray());
        created.put(className, classInstance);
        return classInstance;
    }
}

