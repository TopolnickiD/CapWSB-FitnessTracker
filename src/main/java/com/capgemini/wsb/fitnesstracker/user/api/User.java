package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the fitness tracker application.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * First name of the user. Cannot be null.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Last name of the user. Cannot be null.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Birthdate of the user. Cannot be null.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * Email of the user. Must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * List of trainings associated with the user.
     * Mapped by the 'user' field in the Training entity.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Training> trainings;

    /**
     * Constructs a new User with the specified first name, last name, birthdate, and email.
     *
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param birthdate the birthdate of the user.
     * @param email     the email of the user.
     */
    public User(final String firstName, final String lastName, final LocalDate birthdate, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.trainings = new ArrayList<>();
    }

    /**
     * Adds a training to the user's list of trainings and sets the user in the training.
     *
     * @param training the training to add.
     */
    public void addTraining(Training training) {
        trainings.add(training);
        training.setUser(this);
    }

    /**
     * Removes a training from the user's list of trainings and unsets the user in the training.
     *
     * @param training the training to remove.
     */
    public void removeTraining(Training training) {
        trainings.remove(training);
        training.setUser(null);
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier of the user.
     */
    @Nullable
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique identifier of the user.
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Returns the first name of the user.
     *
     * @return the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the birthdate of the user.
     *
     * @return the birthdate of the user.
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birthdate of the user.
     *
     * @param birthdate the birthdate of the user.
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the list of trainings associated with the user.
     *
     * @return the list of trainings associated with the user.
     */
    public List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Sets the list of trainings associated with the user.
     *
     * @param trainings the list of trainings to set.
     */
    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
