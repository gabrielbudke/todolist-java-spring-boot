package br.com.gabrielsousa.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gabrielsousa.todolist.entities.User;
import br.com.gabrielsousa.todolist.exceptionhandler.UserNotFoundException;
import br.com.gabrielsousa.todolist.repositories.IUserRepository;
import br.com.gabrielsousa.todolist.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class FilterAuthTask extends OncePerRequestFilter {

    private IUserRepository userRepository;

    @Autowired
    public FilterAuthTask(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, UserNotFoundException {

        String path = request.getServletPath();

        if(path.startsWith("/tasks")) {
            // 1. Pegar a autenticação (username e password)
            String authorization = request.getHeader("Authorization");
            String authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

            String authString = new String(authDecoded);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // 2. Validar usuário
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isEmpty()) {
                response.sendError(401, "User unauthorized!");
            } else {
                BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword());
                if(passwordVerify.verified) {
                    request.setAttribute("userId", user.get().getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401, "User unauthorized!");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
