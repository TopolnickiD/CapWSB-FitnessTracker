package com.capgemini.wsb.fitnesstracker.user.api;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * <p>
 * Implementing classes are responsible for executing changes within a database transaction,
 * whether by continuing an existing transaction or creating a new one if required.
 * </p>
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the User entity to be created.
     * @return the created User entity.
     */
    User createUser(final User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted.
     */
    void deleteUser(Long id);

    /**
     * Retrieves users older than the specified age.
     *
     * @param age the age to compare.
     * @return a list of users older than the specified age.
     */
    List<User> getUserOlderThan(Long age);

    /**
     * Updates an existing user.
     *
     * @param user the User entity to be updated.
     * @return the updated User entity.
     */
    User updateUser(User user);
}
