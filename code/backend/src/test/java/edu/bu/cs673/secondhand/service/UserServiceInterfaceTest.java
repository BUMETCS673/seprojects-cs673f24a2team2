package edu.bu.cs673.secondhand.service;

import edu.bu.cs673.secondhand.domain.User;
import edu.bu.cs673.secondhand.mapper.UserMapper;
import edu.bu.cs673.secondhand.model.UserModel;
import edu.bu.cs673.secondhand.service.EmailService;
import edu.bu.cs673.secondhand.service.impl.UserServiceImpl;
import edu.bu.cs673.secondhand.utils.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private SecurityUtil securityUtil;
    @Mock
    private EmailService emailService;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userMapper, securityUtil, emailService);
    }

    @Test
    void registerUser_withValidBUEmail_shouldSucceed() {
        // Arrange
        UserModel userModel = new UserModel();
        userModel.setEmail("test@bu.edu");
        userModel.setUserPassword("password123");
        userModel.setNickname("TestUser");

        when(userMapper.findByEmail(anyString())).thenReturn(null);
        when(securityUtil.encodePassword(anyString())).thenReturn("encodedPassword");
        when(userMapper.insertSelective(any())).thenReturn(1);

        // Act
        boolean result = userService.registerUser(userModel);

        // Assert
        assertTrue(result);
        verify(userMapper).findByEmail("test@bu.edu");
        verify(securityUtil).encodePassword("password123");
        verify(userMapper).insertSelective(any());
        verify(emailService).sendActivationEmail(eq("test@bu.edu"), anyString());
    }

    @Test
    void registerUser_withNonBUEmail_shouldFail() {
        // Arrange
        UserModel userModel = new UserModel();
        userModel.setEmail("test@gmail.com");

        // Act
        boolean result = userService.registerUser(userModel);

        // Assert
        assertFalse(result);
        verify(userMapper, never()).findByEmail(anyString());
        verify(userMapper, never()).insertSelective(any());
        verify(emailService, never()).sendActivationEmail(anyString(), anyString());
    }

    @Test
    void registerUser_withExistingEmail_shouldThrowException() {
        // Arrange
        UserModel userModel = new UserModel();
        userModel.setEmail("existing@bu.edu");
        when(userMapper.findByEmail("existing@bu.edu")).thenReturn(new User());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.registerUser(userModel));
        verify(userMapper).findByEmail("existing@bu.edu");
        verify(userMapper, never()).insertSelective(any());
        verify(emailService, never()).sendActivationEmail(anyString(), anyString());
    }
}