package com.collaboration.java.core;

public class ScopeOfMethods extends OverridingAndUpSizing {

    // It's not possible to override the private method from another class or package.
    // Only protected methods can be converted to the public scope.
    public void canIMakeMethodsPublicFromAnotherPackage() {
        // super.myPrivateMethod();
    }


    // Example of protected method being public now.
    public void makeProtectedPublic() {
        super.thisCanBeMadePublic();
    }
}
