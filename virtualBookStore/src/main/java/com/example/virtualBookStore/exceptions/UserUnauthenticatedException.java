package com.example.virtualBookStore.exceptions;

public class UserUnauthenticatedException extends RuntimeException{
    public UserUnauthenticatedException (String msg){
        super(msg);
    }

}
