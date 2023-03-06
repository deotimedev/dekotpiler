package com.deotime.javatest;

import java.util.Random;

public class JavaTestHelper {

    public Something unknownNullability() {
        return new Random().nextBoolean() ? null : new Something();
    }

    public static class Something {
        public String value = "Hello";
    }

}
