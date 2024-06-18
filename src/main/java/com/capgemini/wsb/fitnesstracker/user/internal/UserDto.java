package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;


/**
 * Data Transfer Object (DTO) for transferring User data.
 * This DTO contains the user's ID, first name, last name, birthdate, and email.
 */
record UserDto(@Nullable Long Id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {
}

/**
 * Data Transfer Object (DTO) for transferring User's name and ID.
 * This DTO contains the user's ID, first name, and last name.
 */
record UserDtoNameAndId(Long Id, String firstName, String lastName) {
}

/**
 * Data Transfer Object (DTO) for updating User data.
 * This DTO allows partial updates of user's first name, last name, birthdate, and email.
 */
record UserUpdateDto(@Nullable String firstName, @Nullable String lastName,
                     @Nullable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                     @Nullable String email) {
}

/**
 * Data Transfer Object (DTO) for transferring User's ID and email.
 * This DTO contains the user's ID and email.
 */
record UserDtoIdAndEmail(Long Id, String email) {
}
