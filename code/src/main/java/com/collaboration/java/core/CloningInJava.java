package com.collaboration.java.core;

public class CloningInJava implements Cloneable {

    private String name;                // Immutable
    private int age;                    // Immutable
    private StringBuilder builder;      // Mutable

    private static boolean DEEP_COPY = false;

    // Simply overriding this method will result in ClassNotSupportedException.
    // TO really make it a Cloneable class you need to implement the class with Cloneable interface.
    // Cloneable is marker interface.
    // calling super.clone() will create a shallow clone.

    // You can also upsize the return type. I have changed it from Object to Cloning in java.
    // Original signature was
    // protected Object clone() throws CloneNotSupportedException.
    @Override
    public Object clone() throws CloneNotSupportedException {
        if (!DEEP_COPY)
            return super.clone();
        else
            return deepClone();
    }

    // In order to create a deep copy you need to call clone on each of the available variable.
    public Object deepClone() {
        CloningInJava cloning = new CloningInJava();
        cloning.name = this.name;
        cloning.age = this.age;
        cloning.builder = new StringBuilder(this.builder);
        return cloning;

    }


    @Override
    public String toString() {
        return "CloningInJava{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", builder=" + builder +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloningInJava original = new CloningInJava();
        original.name = "Gagandeep Singh";
        original.age = 33;
        original.builder = new StringBuilder();
        original.builder.append("Hello");

        CloningInJava clone = (CloningInJava) original.clone();
        clone.name = "Gagan's Clone";
        clone.age = 70;
        clone.builder.append(" World!");

        System.out.println(original);
        System.out.println(clone);

        // Output of the above is:
        // CloningInJava{name='Gagandeep Singh', age=33, builder=Hello World!}
        // CloningInJava{name='Gagan's Clone', age=70, builder=Hello World!}
        // See the last variable. Changing one mutable instance changes other.
        // For this we need to perform a deep copy.


        DEEP_COPY = true;
        System.out.println("After deep copy");

        CloningInJava originalX = new CloningInJava();
        originalX.name = "Gagandeep Singh";
        originalX.age = 33;
        originalX.builder = new StringBuilder();
        originalX.builder.append("Hello");

        CloningInJava cloneX = (CloningInJava) originalX.clone();
        cloneX.name = "Gagan's Clone";
        cloneX.age = 70;
        cloneX.builder.append(" World!");

        System.out.println(originalX);
        System.out.println(cloneX);

        // This is the output after deep copy
        // After deep copy
        // CloningInJava{name='Gagandeep Singh', age=33, builder=Hello}
        // CloningInJava{name='Gagan's Clone', age=70, builder=Hello World!}
    }
}
