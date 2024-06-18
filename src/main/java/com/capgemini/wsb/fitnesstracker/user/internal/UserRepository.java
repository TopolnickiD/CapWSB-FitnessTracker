package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Repository interface for {@link User} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations for {@link User} entities.
 * It also includes custom query methods to find users by email and birthdate.
 * </p>
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email the email of the user to search for.
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if none matched.
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Query searching users by email address with case-insensitive matching and partial matching.
     *
     * @param email the email of the user to search for.
     * @return an {@link Optional} containing the found user or {@link Optional#empty()} if none matched.
     */
    Optional<User> findByEmailIgnoreCaseContaining(String email);

    /**
     * Query searching for users whose birthdate is before the specified date.
     *
     * @param birthdate the date to compare birthdates against.
     * @return a list of users whose birthdate is before the specified date.
     */
    List<User> findByBirthdateBefore(LocalDate birthdate);
}