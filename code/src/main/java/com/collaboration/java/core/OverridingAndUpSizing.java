package com.collaboration.java.core;

public class OverridingAndUpSizing {
    private void myPrivateMethod() {
        System.out.println("This is a private method.");
    }

    protected void thisCanBeMadePublic() {
        System.out.println("You can change access to public also");
    }
}
