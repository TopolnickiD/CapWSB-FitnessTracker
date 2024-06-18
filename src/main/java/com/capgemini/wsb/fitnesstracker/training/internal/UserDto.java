package com.capgemini.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
/**
 * Data transfer object (DTO) for representing user information.
 * This DTO is used for transferring user data between layers of the application.
 */
record UserDto(Long id, String firstName, String lastName,
               String email) {

}
