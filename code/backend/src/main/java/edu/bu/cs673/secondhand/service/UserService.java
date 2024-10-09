package edu.bu.cs673.secondhand.service;

import edu.bu.cs673.secondhand.mapper.UserMapper;
import edu.bu.cs673.secondhand.model.UserModel;
import edu.bu.cs673.secondhand.serviceInterface.EmailServiceInterface;
import edu.bu.cs673.secondhand.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.Date;
import edu.bu.cs673.secondhand.domain.User;

/**
 * Implementation of the UserService interface.
 * This class provides the business logic for user-related operations.
 */
@Service
public class UserServiceInterface implements edu.bu.cs673.secondhand.serviceInterface.UserServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceInterface.class);

    /**
     * The UserMapper used for database operations related to users.
     */
    private final UserMapper userMapper;
    private final SecurityUtil securityUtil;
    private final EmailServiceInterface emailServiceInterface;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userMapper The UserMapper to be used for database operations.
     * @param securityUtil The SecurityUtil to be used for password encoding.
     */
    @Autowired
    public UserServiceInterface(UserMapper userMapper, SecurityUtil securityUtil, EmailServiceInterface emailServiceInterface) {
        this.userMapper = userMapper;
        this.securityUtil = securityUtil;
        this.emailServiceInterface = emailServiceInterface;
    }

    /**
     * Registers a new user in the system.
     *
     * @param userModel The UserModel object containing the user's information.
     * @return true if the registration is successful, false otherwise.
     */
    @Override
    public boolean registerUser(UserModel userModel) {
        // Check if the email is a valid BU email
        if (!isBUEmail(userModel.getEmail())) {
            return false; // Not a BU email
        }

        // Check if user already exists
        User existingUser = userMapper.findByEmail(userModel.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Email already exists");
        }
        
        // Generate activation token
        String activationToken = UUID.randomUUID().toString();
        
        // Set user properties
        userModel.setActivationToken(activationToken);
        userModel.setUserPassword(securityUtil.encodePassword(userModel.getUserPassword()));
        userModel.setCreatedAt(new Date());
        userModel.setUpdatedAt(new Date());
        userModel.setActive(false);
        userModel.setUserStatus((byte) 0); // Assuming 0 means inactive
        
        // Convert UserModel to User
        User user = convertToUser(userModel);
        
        // Insert user into database
        int result = userMapper.insertSelective(user);
        
        if (result > 0) {
            emailServiceInterface.sendActivationEmail(userModel.getEmail(), activationToken);
            return true;
        }
        
        return false;
    }

    /**
     * Activates a user's account.
     *
     * @param email The email address of the user to be activated.
     * @param activationToken The activation token sent to the user's email.
     * @return true if the activation is successful, false otherwise.
     */
    @Override
    public boolean activateUser(String email, String activationToken) {
        User user = userMapper.findByEmailAndToken(email, activationToken);
        if (user == null) {
            return false; // User not found or token doesn't match
        }

        // Check if the token has expired (e.g., 24 hours)
        if (isTokenExpired(user.getCreatedAt())) {
            return false; // Token has expired
        }

        // Activate the user
        user.setActive(true);
        user.setUserStatus((byte) 1); // Assuming 1 means active
        user.setActivationToken(null); // Clear the activation token
        user.setUpdatedAt(new Date());

        int result = userMapper.updateByPrimaryKey(user);
        return result > 0;
    }

    private boolean isTokenExpired(Date createdAt) {
        if (createdAt == null) {
            return true;
        }
        long now = System.currentTimeMillis();
        long tokenCreationTime = createdAt.getTime();
        // Token expires after 24 hours
        return (now - tokenCreationTime) > 24 * 60 * 60 * 1000;
    }

    /**
     * Authenticates a user and logs them into the system.
     *
     * @param email The email address of the user trying to log in.
     * @param password The password provided by the user.
     * @return A UserModel object if authentication is successful, null otherwise.
     */
    @Override
    public UserModel login(String email, String password) {
        // TODO: Implement user login logic
        return null;
    }

    /**
     * Initiates the password reset process for a user.
     *
     * @param email The email address of the user requesting a password reset.
     * @return true if the password reset process is initiated successfully, false otherwise.
     */
    @Override
    public boolean initiatePasswordReset(String email) {
        // TODO: Implement password reset initiation logic
        return false;
    }

    /**
     * Resets a user's password.
     *
     * @param email The email address of the user resetting their password.
     * @param resetToken The reset token sent to the user's email.
     * @param newPassword The new password chosen by the user.
     * @return true if the password is reset successfully, false otherwise.
     */
    @Override
    public boolean resetPassword(String email, String resetToken, String newPassword) {
        // TODO: Implement password reset logic
        return false;
    }

    private User convertToUser(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setEmail(userModel.getEmail());
        user.setUserPassword(userModel.getUserPassword());
        user.setNickname(userModel.getNickname());
        user.setAvatar(userModel.getAvatar());
        user.setSignInTime(userModel.getSignInTime());
        user.setUserStatus(userModel.getUserStatus());
        user.setActive(userModel.isActive());
        user.setActivationToken(userModel.getActivationToken());
        user.setCreatedAt(userModel.getCreatedAt());
        user.setUpdatedAt(userModel.getUpdatedAt());
        return user;
    }

    private boolean isBUEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@bu.edu");
    }

    @Override
    public void initiateSignUp(String email) {
        User user = new User();
        user.setEmail(email);
        user.setAccountNumber(generateAccountNumber()); // 生成唯一的账号
        user.setActive(false);  // 使用正确的方法名
        String token = generateActivationToken();
        user.setActivationToken(token);
        user.setTokenExpiry(calculateTokenExpiry()); // 设置token过期时间
        user.setCreatedAt(new Date());
        user.setUserPassword(""); // 设置一个空字符串作为初始密码
        
        // 设置初始昵称为邮箱的用户名部分
        String initialNickname = email.split("@")[0];
        user.setNickname(initialNickname);

        // 设置默认头像
        user.setAvatar("default_avatar.png");

        try {
            userMapper.insertSelective(user);
            emailServiceInterface.sendSignUpEmail(email, token);
        } catch (Exception e) {
            logger.error("Error inserting user: " + email, e);
            throw new RuntimeException("Failed to create user account.");
        }
    }

    @Override
    public void completeSignUp(String email, String token, String password, String nickname) {
        User user = userMapper.findByEmailAndToken(email, token);
        if (user == null) {
            throw new RuntimeException("Invalid sign-up link");
        }
        
        Boolean isActive = user.isActive();
        if (isActive != null && isActive) {
            throw new RuntimeException("User is already active");
        }

        if (isTokenExpired(user.getCreatedAt())) {
            throw new RuntimeException("Sign-up link has expired");
        }

        user.setUserPassword(securityUtil.encodePassword(password));
        user.setNickname(nickname);
        user.setActive(true);
        user.setUserStatus((byte) 1);
        user.setActivationToken(null);
        user.setUpdatedAt(new Date());

        userMapper.updateByPrimaryKey(user);
    }

    private String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.format("%08d", (int)(Math.random() * 100000000));
        } while (userMapper.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    private Date calculateTokenExpiry() {
        // 设置token过期时间，例如24小时后
        return new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
    }

    private String generateActivationToken() {
        // Generate a UUID as the activation token
        return UUID.randomUUID().toString();
    }
}