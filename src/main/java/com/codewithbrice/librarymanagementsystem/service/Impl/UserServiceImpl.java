package com.codewithbrice.librarymanagementsystem.service.Impl;

import com.codewithbrice.librarymanagementsystem.mapper.UserMapper;
import com.codewithbrice.librarymanagementsystem.modal.User;
import com.codewithbrice.librarymanagementsystem.payload.dto.UserDTO;
import com.codewithbrice.librarymanagementsystem.repository.UserRepository;
import com.codewithbrice.librarymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user==null) {
            throw new Exception("user not found!");
        }
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(
                UserMapper::toDTO
        ).collect(Collectors.toList());
    }
}
