package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between User entities and various User DTOs.
 * This class provides methods to map User entities to their corresponding Data Transfer Objects (DTOs)
 * and vice versa, facilitating data transformation between different layers of the application.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert.
     * @return the corresponding UserDto.
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto to convert.
     * @return the corresponding User entity.
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Converts a User entity to a UserDtoNameAndId.
     *
     * @param user the User entity to convert.
     * @return the corresponding UserDtoNameAndId.
     */
    UserDtoNameAndId toNameAndIdDto(User user) {
        return new UserDtoNameAndId(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a User entity to a UserDtoIdAndEmail.
     *
     * @param user the User entity to convert.
     * @return the corresponding UserDtoIdAndEmail.
     */
    UserDtoIdAndEmail toIdAndEmailDto(User user){
        return new UserDtoIdAndEmail(user.getId(), user.getEmail());
    }

    /**
     * Updates a User entity with data from a UserUpdateDto.
     *
     * @param userDto the UserUpdateDto containing the updated data.
     * @param user the User entity to update.
     * @return the updated User entity.
     */
    public User updateUserFromDto(UserUpdateDto userDto, User user) {
        if (userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }
        if (userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }
        if (userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }
        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }
        return user;
    }
}
