package br.com.login.auth.resource;

import br.com.login.auth.model.LoginRequest;
import br.com.login.auth.model.User;
import br.com.login.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/create")
    public User create(@RequestBody User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = repository.findByUsername(loginRequest.getUsername());
        BCryptPasswordEncoder passwordEncoder = passwordEncoder();
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }




}
