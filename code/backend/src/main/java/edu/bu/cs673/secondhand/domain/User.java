package edu.bu.cs673.secondhand.domain;

import java.util.Date;
import java.util.List;

/**
 * User class represents a user in the system with various attributes.
 * Author: YQ
 */
public class User {
    private Long id;  // Unique identifier for the user

    private String accountNumber;  // User's account number

    private String userPassword;  // User's password

    private String nickname;  // User's nickname

    private String avatar;  // URL to the user's avatar image

    private Date signInTime;  // Timestamp of the user's last sign-in

    private Byte userStatus;  // Status of the user (e.g., active, inactive)

    private Date createdAt;  // Timestamp when the user was created

    private Date updatedAt;  // Timestamp when the user was last updated

    private String email;  // User's email address

    private boolean isActive;  // Indicates if the user's account is active

    private Date tokenExpiry;  // Expiry date of the activation token

    private String activationToken;  // Activation token for the user

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public Byte getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Byte userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {  // Setter for updatedAt
        this.updatedAt = updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getTokenExpiry() {
        return tokenExpiry;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTokenExpiry(Date tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public List<User> findUserByList(List<Long> idList) {
        return List.of();
    }
}