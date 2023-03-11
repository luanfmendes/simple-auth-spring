package br.com.login.auth.repository;

import br.com.login.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
