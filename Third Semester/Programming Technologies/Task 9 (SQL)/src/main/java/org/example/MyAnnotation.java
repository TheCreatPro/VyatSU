package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// тема: Аннотации
@Retention(RetentionPolicy.RUNTIME) // Где видна аннотация. RUNTIME - везде
@Target(ElementType.METHOD)  // Type - class, Method - методы класса. fields - поля класса
// если аннотацию нужна для полей, то fields, если для методов method
public @interface MyAnnotation {

}
