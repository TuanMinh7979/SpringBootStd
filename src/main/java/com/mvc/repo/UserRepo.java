package com.mvc.repo;

import java.util.Optional;

import com.mvc.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u join fetch u.roles r join fetch r.permissions where u.username= :username")
    Optional<User> findByUsernameWithPermission(@Param("username") String username);

    boolean existsByUsername(String username);
}
