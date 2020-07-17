package org.example.chat.service.friendservice;

import org.example.chat.model.Friends;
import org.example.chat.model.User;

import java.util.List;

public interface FriendsService {
    void add(Friends friend);
    List<User> showMyFriends(Long myId);
    boolean friendship (Friends friend);
    List<User> findFriend(String firstName);
    void deletedFriendships(Long myId, Long friendId);
}
