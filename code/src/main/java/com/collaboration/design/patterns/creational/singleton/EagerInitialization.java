package com.collaboration.design.patterns.creational.singleton;

public class EagerInitialization implements Cloneable {

    private static final EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {
    }

    public static EagerInitialization getInstance() {
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // super.clone() method will not work until you implement the Cloneable interface.
        // If Cloneable inteface is not implemented it will throw ClassNotSupportedException.
        return super.clone();
    }

    public static void main(String[] args) {
        try {
            EagerInitialization instance1 = getInstance();
            EagerInitialization instance2 = (EagerInitialization) getInstance().clone();

            System.out.println(instance1.hashCode());
            System.out.println(instance2.hashCode());

        } catch (CloneNotSupportedException e) {
            // Clone not supported is a checked exception.
            System.out.println("Clone is not yet supported here.");
        }
    }
}
