package br.com.gabrielsousa.todolist.repositories;

import br.com.gabrielsousa.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    // User findByUsername(String username);

}
