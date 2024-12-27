package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main { // тема: Аннотации
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        // если берём класс по имени надо пробросить экспешн ClassNotFoundException
        // если надо ВСЕ поля или аннотации: .getDeclaredFields()

        Class testClass = TestClass.class;
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.isAnnotationPresent(MyAnnotationParam.class)) // если есть myannotationparam то принтим ДА
            {
                System.out.println("   ---YES");
                // method.invoke(null);
            }
        }
    }
}