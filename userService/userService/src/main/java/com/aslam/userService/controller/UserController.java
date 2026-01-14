package com.aslam.userService.controller;

import com.aslam.userService.model.User;
import com.aslam.userService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE USER
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET USER BY ID (UUID)
    int retryCount = 1;

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/{id}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
    public User getUser(@PathVariable UUID id) {
        logger.info("Retry count: {}", retryCount);
        retryCount++;

        return userService.getUserById(id);
    }

    // Fallback method for Circuit Breaker
    public User ratingHotelFallback(UUID id, Throwable ex) {
        logger.info("Fallback executed because service is down: {}", ex.getMessage());
        return User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created because some service is down")
                .userId(id)
                .build();
    }

    // UPDATE USER (UUID)
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable UUID id) {
        return userService.updateUser(user, id);
    }

    // DELETE USER (UUID)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
