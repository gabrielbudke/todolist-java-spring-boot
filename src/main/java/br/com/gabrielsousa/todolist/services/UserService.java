package br.com.gabrielsousa.todolist.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gabrielsousa.todolist.entities.User;
import br.com.gabrielsousa.todolist.exceptionhandler.UserAlreadyExistsException;
import br.com.gabrielsousa.todolist.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository IUserRepository) {
        this.userRepository = IUserRepository;
    }

    public User create(User user) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists!");
        }

        String passwordEncoded = BCrypt.withDefaults().hashToString(16, user.getPassword().toCharArray());

        user.setPassword(passwordEncoded);

        return userRepository.save(user);
    }

}
