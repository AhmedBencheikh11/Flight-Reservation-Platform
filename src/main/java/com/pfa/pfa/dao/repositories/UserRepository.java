package com.pfa.pfa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pfa.pfa.dao.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
    boolean existsByMail(String mail);
}
