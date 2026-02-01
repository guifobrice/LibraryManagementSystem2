package com.codewithbrice.librarymanagementsystem.service;

import com.codewithbrice.librarymanagementsystem.exception.UserException;
import com.codewithbrice.librarymanagementsystem.payload.dto.UserDTO;
import com.codewithbrice.librarymanagementsystem.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;
    void createdPasswordResetToken(String email) throws UserException;
    void resetPassword(String token, String newPassword) throws Exception;

}
