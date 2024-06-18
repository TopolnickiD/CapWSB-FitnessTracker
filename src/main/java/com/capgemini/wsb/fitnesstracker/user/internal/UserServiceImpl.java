package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling operations on {@link User} entities.
 * This class implements the {@link UserFacade} interface which extends both {@link UserService} and {@link UserProvider},
 * providing methods to create, delete, update, and retrieve users from the database.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserFacade {
    private final UserRepository userRepository;

    /**
     * Creates a new user.
     *
     * @param user the User entity to be created.
     * @return the created User entity.
     * @throws IllegalArgumentException if the user already has an ID.
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @Override
    public void deleteUser(Long id) {
        log.info("Deleting User with ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Retrieves users older than the specified age.
     *
     * @param age the age to compare.
     * @return a list of users older than the specified age.
     */
    @Override
    public List<User> getUserOlderThan(Long age) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdate = currentDate.minusYears(age);
        return userRepository.findByBirthdateBefore(birthdate);
    }

    /**
     * Updates an existing user.
     *
     * @param user the User entity to be updated.
     * @return the updated User entity.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if none matched.
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user to retrieve.
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if none matched.
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCaseContaining(email);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users.
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
