package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enumeration representing different types of activities.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tennis");

    private final String displayName;

    /**
     * Constructs an ActivityType with its display name.
     *
     * @param displayName the display name of the activity type.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of the activity type.
     *
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

}

