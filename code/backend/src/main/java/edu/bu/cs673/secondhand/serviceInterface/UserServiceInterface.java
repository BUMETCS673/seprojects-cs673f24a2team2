package edu.bu.cs673.secondhand.service;

import edu.bu.cs673.secondhand.model.UserModel;

public interface UserService {
    /**
     * Register a new user
     * @param user The user to register
     * @return true if registration is successful, false otherwise
     */
    boolean registerUser(UserModel user);

    /**
     * Activate a user account
     * @param email The user's email
     * @param activationToken The activation token
     * @return true if activation is successful, false otherwise
     */
    boolean activateUser(String email, String activationToken);

    /**
     * Authenticate a user
     * @param email The user's email
     * @param password The user's password
     * @return The authenticated UserModel if successful, null otherwise
     */
    UserModel login(String email, String password);

    /**
     * Initiate the password reset process
     * @param email The user's email
     * @return true if password reset process is initiated successfully, false otherwise
     */
    boolean initiatePasswordReset(String email);

    /**
     * Reset the user's password
     * @param email The user's email
     * @param resetToken The reset token
     * @param newPassword The new password
     * @return true if password is reset successfully, false otherwise
     */
    boolean resetPassword(String email, String resetToken, String newPassword);

    void initiateSignUp(String email);
    void completeSignUp(String email, String token, String password, String nickname);
}
