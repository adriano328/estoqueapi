package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Role;
import com.estoque.estoque_api.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}
