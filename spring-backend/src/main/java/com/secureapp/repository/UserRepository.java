package com.secureapp.repository;

import com.secureapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository responsible for managing user persistence.
 *
 * For this workshop we use an in-memory data store
 * implemented with a thread-safe ConcurrentHashMap.
 */
@Repository
public class UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    /**
     * Finds a user by username.
     *
     * @param username the username
     * @return Optional user
     */
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    /**
     * Saves a user in the repository.
     *
     * @param user the user to store
     */
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Checks if a user exists.
     *
     * @param username username
     * @return true if exists
     */
    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }
}