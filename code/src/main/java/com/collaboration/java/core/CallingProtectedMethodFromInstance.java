package com.collaboration.java.core;

public class CallingProtectedMethodFromInstance extends OverridingAndUpSizing {

    // Also I can override the protected method in this class.
//    @Override
//    protected void thisCanBeMadePublic() {
//        super.thisCanBeMadePublic();
//    }

    public static void main(String[] args) {
        CallingProtectedMethodFromInstance instance = new CallingProtectedMethodFromInstance();
        instance.thisCanBeMadePublic();
    }
}
