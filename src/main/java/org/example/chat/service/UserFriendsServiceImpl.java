package org.example.chat.service;

import org.example.chat.model.User;
import org.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFriendsServiceImpl implements UserFriendsService{
    @Autowired
    private UserRepository userRepository;

    public List<Optional<User>> findAllBesidesMe(Long id) {
        return userRepository.findAllBesidesMe(id);
    }
}
