package org.example.chat.repository;

import org.example.chat.model.Friends;
import org.example.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends,Long> {

    @Query(value = "select * from friends where my_id = ?1 And friend_id = ?2",nativeQuery = true)
    Optional<Friends> findFriendships(Long myId, Long friendId);
    @Query(value = "select friend_id from friends where my_id = ?1 ",nativeQuery = true)
    List<Optional<Long>> findAllFriends(Long myId);

}
