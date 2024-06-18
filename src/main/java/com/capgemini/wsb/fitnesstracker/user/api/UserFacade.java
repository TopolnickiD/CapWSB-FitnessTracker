package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Combines the functionalities of {@link UserProvider} and {@link UserService}.
 * This interface is created to ensure that a class implementing both
 * {@link UserProvider} and {@link UserService} can be used through a single
 * interface, adhering to the SOLID principles.
 */
public interface UserFacade extends UserProvider, UserService{
}
