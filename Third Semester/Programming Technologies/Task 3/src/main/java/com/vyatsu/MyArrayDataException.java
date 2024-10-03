package com.vyatsu;

public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(String message)  // неправильный размер массива
    {
        super(message);
    }
}
