package com.vyatsu;

public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException(String message)  // в ячейке массива лежит что-то не то
    {
        super(message);
    }
}
