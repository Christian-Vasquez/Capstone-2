package com.company.customerservice.exception;

public class NotFoundException  extends RuntimeException{

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}