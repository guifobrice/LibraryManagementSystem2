package com.codewithbrice.librarymanagementsystem.exception;

public class UserException extends Throwable{

    public UserException(String emailIdAlreadyRegistered) {
        super(emailIdAlreadyRegistered);

    }
}
