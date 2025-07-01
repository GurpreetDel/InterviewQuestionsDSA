package com.boot;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * This class demonstrates TestNG features including:
 * - Test groups
 * - Test priorities
 * - Parameterized tests
 */
public class LoginTest {

    /**
     * Setup method that runs before each test method
     */
    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up test environment");
    }

    /**
     * Cleanup method that runs after each test method
     */
    @AfterMethod
    public void tearDown() {
        System.out.println("Cleaning up test environment");
    }

    /**
     * Test valid login with parameters from XML
     * - Priority: 1 (runs first)
     * - Group: login, positive
     */
    @Test(priority = 1, groups = {"login", "positive"})
    @Parameters({"username", "password"})
    public void testValidLogin(String username, String password) {
        System.out.println("Testing valid login with username: " + username + " and password: " + password);
        // Simulate login logic
        boolean loginSuccess = true; // In a real test, this would be the result of actual login attempt
        assertTrue(loginSuccess, "Login should be successful with valid credentials");
    }

    /**
     * Test invalid login with hardcoded values
     * - Priority: 2 (runs second)
     * - Group: login, negative
     */
    @Test(priority = 2, groups = {"login", "negative"})
    public void testInvalidLogin() {
        System.out.println("Testing invalid login");
        // Simulate login logic with invalid credentials
        boolean loginSuccess = false; // In a real test, this would be the result of actual login attempt
        assertFalse(loginSuccess, "Login should fail with invalid credentials");
    }

    /**
     * Test account lockout after multiple failed attempts
     * - Priority: 3 (runs third)
     * - Group: security
     */
    @Test(priority = 3, groups = {"security"})
    public void testAccountLockout() {
        System.out.println("Testing account lockout after multiple failed attempts");
        // Simulate account lockout logic
        boolean accountLocked = true; // In a real test, this would be the result of actual lockout check
        assertTrue(accountLocked, "Account should be locked after multiple failed login attempts");
    }

    /**
     * Test password reset functionality
     * - Priority: 4 (runs fourth)
     * - Group: account
     */
    @Test(priority = 4, groups = {"account"})
    public void testPasswordReset() {
        System.out.println("Testing password reset functionality");
        // Simulate password reset logic
        boolean resetSuccess = true; // In a real test, this would be the result of actual reset attempt
        assertTrue(resetSuccess, "Password reset should be successful");
    }
}