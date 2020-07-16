package org.example.chat.service;

import org.example.chat.model.User;

import java.util.List;
import java.util.Optional;

public interface UserFriendsService {
    public List<Optional<User>> findAllBesidesMe(Long id);
}
