package com.collaboration.design.patterns.creational;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Ensure single instance of the class is created in a JVM.
public class Singleton {

    // 1. Eager initialization
    private static class EagerInitialization {

        // Problem with this is that even if the instance is not required, it will creat one during the class loading.
        // Doesn't provide a way to handle the exception (might be thrown during instance creation)
        private static final EagerInitialization instance = new EagerInitialization();

        private EagerInitialization() {
        }

        public static EagerInitialization getInstance() {
            return instance;
        }
    }

    // 2. Static block initialization.
    private static class StaticBlockInitialization {

        private static StaticBlockInitialization instance;

        // Though it removes the exception handling problem for us from the previous solution, it still is created even
        // if we do not need the instance.
        static {
            try {
                instance = new StaticBlockInitialization();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private StaticBlockInitialization() {
        }

        public static StaticBlockInitialization getInstance() {
            return instance;
        }
    }

    // 3. Lazy initialization
    private static class LazyInitialization {

        private static LazyInitialization instance;

        private LazyInitialization() {
        }

        // This approach is fine for single threaded application, but can cause problem when multiple threads are in
        // the if condition block.
        public static LazyInitialization getInstance() {
            if (instance == null) {
                instance = new LazyInitialization();
            }
            return instance;
        }
    }

    private static class ThreadSafeLazyInitialization {

        private static ThreadSafeLazyInitialization instance;

        private ThreadSafeLazyInitialization() {
        }

        // This is thread safe now, but synchronizing the whole method is not good for performance. So instead of
        // synchronizing the whole method we should only synchronize the block, to do so we need to provide a double
        // null check.
        public static synchronized ThreadSafeLazyInitialization getInstance() {
            if (instance == null) {
                instance = new ThreadSafeLazyInitialization();
            }
            return instance;
        }
    }

    private static class ThreadSafeSyncBlock {

        // This needs to be volatile, otherwise some thread might see it's half initialized state.
        // If you remember data is temp stored in cache or registers before sending to memory.
        // That's the kind of pitfall.
        private static volatile ThreadSafeSyncBlock instance;

        private ThreadSafeSyncBlock() {
        }

        // This is also called double checked locking of Singleton.
        // Without volatile it's still broken.
        public static ThreadSafeSyncBlock getInstance() {

            // This is not thread safe hence first barrier.
            if (instance == null) {
                synchronized (ThreadSafeSyncBlock.class) {
                    // While instantiation was taking place another thread might have entered the first barrier, so
                    // this barrier to again check.
                    if (instance == null)
                        instance = new ThreadSafeSyncBlock();
                }
            }
            return instance;
        }
    }

    // Prior to Java 5, java memory model had a lot of issues and the above approaches used to fail in certain
    // scenarios where too many threads try to get the instance of the Singleton class simultaneously
    private static class InnerStaticClass {

        private InnerStaticClass() {
        }

        // This class in not loaded into memory until someone calls the getInstance() method of InnerStaticClass.
        private static class SingletonHelper {
            private static final InnerStaticClass INSTANCE = new InnerStaticClass();
        }

        public static InnerStaticClass getInstance() {
            return SingletonHelper.INSTANCE;
        }

    }

    private static class BreakingSingletonUsingReflection {

        // Example to break the singleton pattern using the reflection.
        // Except for the Enum created singleton.
        public void breakingBad() {

            InnerStaticClass innerStaticClass1 = InnerStaticClass.getInstance();
            InnerStaticClass innerStaticClass2 = null;
            InnerStaticClass innerStaticClass4 = null;

            System.out.println(innerStaticClass1.hashCode());
//            System.out.println(innerStaticClass2.hashCode());

            try {
                // This is using the class instance. Is now deprecated
                innerStaticClass2 = InnerStaticClass.class.newInstance();

                // This is using the class constructor. Need to set it accessible.
                // Both of them provide the same newInstance() method for creation of the new instance.
                // newInstance is equivalent to new ClassName();
                Constructor<InnerStaticClass> innerStaticConstructor = InnerStaticClass.class.getDeclaredConstructor();
                innerStaticConstructor.setAccessible(true);
                innerStaticClass4 = innerStaticConstructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            System.out.println(innerStaticClass1.hashCode());
            System.out.println(innerStaticClass2.hashCode());
            System.out.println(InnerStaticClass.getInstance().hashCode());
            System.out.println(innerStaticClass4.hashCode());
        }
    }

    // All the above singleton patterns can be compromised using the reflection.
    // Only way to be safe from reflection is using the enum.
    private static class EnumSingleton {
        public enum EnumSingle {
            INSTANCE;
            public static void doSomething() {
            }
        }
    }

    // Test to check if I don't override the clone method will it work?
    public static void cloning() {
        EagerInitialization initialization = EagerInitialization.getInstance();
//        initialization.clone();
        // You can not call the clone method without implementing the Cloneable interface.
//        EagerInitialization instance2 = initialization.clone();
    }

    private static class EagerInitializationWithCloning {
    }


    public static void main(String[] args) {
        BreakingSingletonUsingReflection test = new BreakingSingletonUsingReflection();
        test.breakingBad();
    }
}
