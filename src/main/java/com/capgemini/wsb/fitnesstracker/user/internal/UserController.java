package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserFacade;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 * This controller provides endpoints to handle user-related operations such as
 * creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserController {

    // Tworzę jeden interfejs, który rozszerza UserService i UserProvider, aby móc odnieść się do UserServiceImpl używając jednego interfejsu (zachowując zasadę ISP).
    private final UserFacade userService;
    private final UserMapper userMapper;

    /**
     * Retrieves all users.
     *
     * @return a list of all users.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userService.findAllUsers()
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error retrieving all users", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all users with only their names and IDs.
     *
     * @return a list of users with names and IDs.
     */
    @GetMapping("/nameAndId")
    public ResponseEntity<List<UserDtoNameAndId>> getAllUsersNameAndId() {
        try {
            List<UserDtoNameAndId> users = userService.findAllUsers()
                    .stream()
                    .map(userMapper::toNameAndIdDto)
                    .toList();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error retrieving all users name and ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return the user with the specified ID.
     */
    @GetMapping("/ids/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            UserDto user = userService.getUser(id)
                    .map(userMapper::toDto)
                    .orElseThrow(() -> new UserNotFoundException(id));
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            log.error("User not found with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error retrieving user by id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return a response entity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            log.error("User not found with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error deleting user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email the email of the user to retrieve.
     * @return the user with the specified email.
     */
    @GetMapping("/emails/{email}")
    public ResponseEntity<UserDtoIdAndEmail> getUserByEmail(@PathVariable String email) {
        try {
            UserDtoIdAndEmail user = userService.getUserByEmail(email)
                    .map(userMapper::toIdAndEmailDto)
                    .orElseThrow(() -> new UserNotFoundException(email));
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            log.error("User not found with email: {}", email, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error retrieving user by email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Adds a new user.
     *
     * @param userDto the user data to add.
     * @return the created user.
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        try {
            User user = userMapper.toEntity(userDto);
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            log.error("Invalid user data", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Error creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves users older than the specified age.
     *
     * @param age the age to compare.
     * @return a list of users older than the specified age.
     */
    @GetMapping("/{age}")
    public ResponseEntity<List<UserDtoNameAndId>> getUsersOlderThan(@PathVariable Long age) {
        try {
            List<UserDtoNameAndId> users = userService.getUserOlderThan(age)
                    .stream()
                    .map(userMapper::toNameAndIdDto)
                    .toList();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error retrieving users older than age", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update.
     * @param userDto the user data to update.
     * @return the updated user.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        try {
            User userToUpdate = userService.getUser(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            User updatedUser = userService.updateUser(userMapper.updateUserFromDto(userDto, userToUpdate));
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            log.error("User not found with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error updating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
