package com.jimg.myalbatross.modules.user.domain.repository;

import com.jimg.myalbatross.modules.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByMail(String mail);

    Optional<User> findByMail(String mail);
}
