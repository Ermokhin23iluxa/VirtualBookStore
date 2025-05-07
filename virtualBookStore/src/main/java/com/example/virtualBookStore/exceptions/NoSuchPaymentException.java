package com.example.virtualBookStore.exceptions;

public class NoSuchPaymentException extends RuntimeException{
    public NoSuchPaymentException(Long orderId) {super("Ошибка по оплате заказа с id:"+orderId);}
}
