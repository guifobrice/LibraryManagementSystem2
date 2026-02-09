package com.codewithbrice.librarymanagementsystem.service;

import com.codewithbrice.librarymanagementsystem.modal.User;
import com.codewithbrice.librarymanagementsystem.payload.dto.UserDTO;

import java.util.List;

public interface UserService {

    public User getCurrentUser() throws Exception;
    public List<UserDTO> getAllUsers();
}
