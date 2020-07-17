package org.example.chat.service.friendservice;

import org.example.chat.model.Friends;
import org.example.chat.model.User;
import org.example.chat.repository.FriendsRepository;
import org.example.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void add(Friends friend) {
        friendsRepository.save(friend);
    }
    @Override
    public List<User> showMyFriends(Long myId) {
        List<Optional<Long>> optionalList = friendsRepository.findAllFriends(myId);
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <optionalList.size() ; i++) {
           userList.add(userRepository.getOne(optionalList.get(i).get().longValue()));
        }
        return userList;
    }

    @Override
    public boolean friendship(Friends friend) {
        return friendsRepository.findFriendships(
                friend.getMyId(), friend.getFriendId()).isPresent() ;

    }

    @Override
    public List<User> findFriend(String firstName) {
        List<Optional<User>> userList = userRepository.findAllByName(firstName);
        List<User> users = new ArrayList<>();
        for (Optional<User> u : userList){
            users.add(u.get());
        }
        return users;
    }

    @Override
    public void deletedFriendships(Long myId, Long friendId) {
      Optional<Friends> friends =  friendsRepository.findFriendships(myId, friendId);
      friendsRepository.delete(friends.get());
    }
}
