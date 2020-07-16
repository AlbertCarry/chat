package org.example.chat.repository;

import org.example.chat.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository  extends JpaRepository<Messages,Long> {

//    @Query(value = "SELECT * FROM messages WHERE from_id = ?1 AND to_id = ?2" , nativeQuery = true)
    @Query(value =
            "select * from messages where from_id = ?1 and to_id = ?2 or from_id = ?2 and to_id = ?1"
            ,nativeQuery = true)
    List<Optional<Messages>> getAllById(Long from_id, Long to_id);
}
