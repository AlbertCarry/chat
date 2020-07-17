package org.example.chat.repository;

import org.example.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user_spau WHERE login = ?1 AND hash_password = ?2",nativeQuery = true)
    Optional<User> getByLoginAndPassword(String login,String password);

    @Query(value = "SELECT * FROM user_spau WHERE login = ?1",nativeQuery = true)
    Optional<User> findByLogin(String login);

    @Query(value = "select * from user_spau where id != ?1",nativeQuery = true)
    List<Optional<User>> findAllBesidesMe(Long id);
    @Query(value = "select * from user_spau where first_name = ?1 or last_name = ?1 ",nativeQuery = true)
    List<Optional<User>> findAllByName(String firstName);

}
