package com.boot;

import com.boot.util.XmlCredentialReader;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

/**
 * This class demonstrates how to use XML data with TestNG DataProvider
 */
public class XmlDataProviderTest {

    /**
     * DataProvider that reads credentials from XML file
     * 
     * @return Array of credential objects (username, password, email)
     */
    @DataProvider(name = "credentialsProvider")
    public Object[][] credentialsProvider() {
        // Read credentials from XML file
        List<Map<String, String>> users = XmlCredentialReader.readCredentials("credentials.xml");
        
        // Convert to Object[][] for DataProvider
        Object[][] data = new Object[users.size()][3];
        for (int i = 0; i < users.size(); i++) {
            Map<String, String> user = users.get(i);
            data[i][0] = user.get("username");
            data[i][1] = user.get("password");
            data[i][2] = user.get("email");
        }
        
        return data;
    }
    
    /**
     * Test login with credentials from XML file using DataProvider
     * - Group: dataprovider
     * 
     * @param username Username from XML
     * @param password Password from XML
     * @param email Email from XML
     */
    @Test(dataProvider = "credentialsProvider", groups = {"dataprovider"})
    public void testLoginWithXmlCredentials(String username, String password, String email) {
        System.out.println("Testing login with credentials from XML:");
        System.out.println("  Username: " + username);
        System.out.println("  Password: " + password);
        System.out.println("  Email: " + email);
        
        // Simulate login logic
        boolean loginSuccess = true; // In a real test, this would be the result of actual login attempt
        assertTrue(loginSuccess, "Login should be successful with credentials from XML");
    }
    
    /**
     * Test with credentials directly from testng.xml parameters
     * - Group: parameters
     * 
     * @param username Username from testng.xml
     * @param password Password from testng.xml
     */
    @Test(groups = {"parameters"})
    @Parameters({"username", "password"})
    public void testWithXmlParameters(String username, String password) {
        System.out.println("Testing with parameters from testng.xml:");
        System.out.println("  Username: " + username);
        System.out.println("  Password: " + password);
        
        // Simulate some test logic
        boolean success = true;
        assertTrue(success, "Test with XML parameters should be successful");
    }
    
    /**
     * Setup method that runs before each test method
     */
    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up XML data provider test");
    }
    
    /**
     * Cleanup method that runs after each test method
     */
    @AfterMethod
    public void tearDown() {
        System.out.println("Cleaning up XML data provider test");
    }
}