package org.example;

public class TestClass {
    @MyAnnotation
    public static void method1() {
        System.out.println("method1");
    }

    @MyAnnotation
    @MyAnnotationParam
    public static void method2() {
        System.out.println("method2");
    }

    @MyAnnotation
    @MyAnnotationParam(priority=3)  // сначала запускаются с высоким приоритетом
    public static void method3() {
        System.out.println("method3");
    }
}
