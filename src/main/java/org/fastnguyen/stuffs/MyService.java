package org.fastnguyen.stuffs;

public class MyService {

    private final int someValue;

    public MyService(int someValue) {
        this.someValue = someValue;
    }

    public void printValue() {
        System.out.println("The injected value is: " + someValue);
    }
}
