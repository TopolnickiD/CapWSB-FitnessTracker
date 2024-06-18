package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Entity class representing a training session.
 * <p>
 * This class is mapped to the "trainings" table in the database and contains information about
 * the user performing the training, start and end times, activity type, distance, and average speed.
 * </p>
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("trainings")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Constructs a Training object with a specified user, start time, end time, activity type, distance, and average speed.
     *
     * @param user         the user performing the training.
     * @param startTime    the start time of the training session.
     * @param endTime      the end time of the training session.
     * @param activityType the type of activity performed during the training.
     * @param distance     the distance covered during the training.
     * @param averageSpeed the average speed during the training.
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Constructs a Training object with a specified start time, end time, activity type, distance, and average speed.
     *
     * @param startTime    the start time of the training session.
     * @param endTime      the end time of the training session.
     * @param activityType the type of activity performed during the training.
     * @param distance     the distance covered during the training.
     * @param averageSpeed the average speed during the training.
     */
    public Training(Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Retrieves the start time of the training session.
     *
     * @return the start time of the training session.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the training session.
     *
     * @param startTime the start time to set.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the training session.
     *
     * @return the end time of the training session.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the training session.
     *
     * @param endTime the end time to set.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the activity type of the training session.
     *
     * @return the activity type of the training session.
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activity type of the training session.
     *
     * @param activityType the activity type to set.
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Retrieves the distance covered during the training session.
     *
     * @return the distance covered during the training session.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance covered during the training session.
     *
     * @param distance the distance to set.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Retrieves the average speed during the training session.
     *
     * @return the average speed during the training session.
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Sets the average speed during the training session.
     *
     * @param averageSpeed the average speed to set.
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Retrieves the user who performed the training session.
     *
     * @return the user who performed the training session.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who performed the training session.
     *
     * @param user the user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}