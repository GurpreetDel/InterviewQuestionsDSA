package com.boot.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class to read credentials from an XML file
 */
public class XmlCredentialReader {

    /**
     * Reads all users from the credentials.xml file
     * 
     * @param xmlFilePath Path to the XML file containing credentials
     * @return List of user credential maps (username, password, email)
     */
    public static List<Map<String, String>> readCredentials(String xmlFilePath) {
        List<Map<String, String>> users = new ArrayList<>();
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            // Normalize the XML structure
            doc.getDocumentElement().normalize();
            
            // Get all user elements
            NodeList userList = doc.getElementsByTagName("user");
            
            // Process each user
            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);
                
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;
                    Map<String, String> user = new HashMap<>();
                    
                    // Extract username
                    user.put("username", getElementValue(userElement, "username"));
                    
                    // Extract password
                    user.put("password", getElementValue(userElement, "password"));
                    
                    // Extract email
                    user.put("email", getElementValue(userElement, "email"));
                    
                    users.add(user);
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Error reading credentials XML: " + e.getMessage());
            e.printStackTrace();
        }
        
        return users;
    }
    
    /**
     * Gets the text value of an element
     * 
     * @param element Parent element
     * @param tagName Name of the child tag
     * @return Text content of the child element
     */
    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            NodeList childNodes = nodeList.item(0).getChildNodes();
            if (childNodes.getLength() > 0) {
                return childNodes.item(0).getNodeValue();
            }
        }
        return "";
    }
    
    /**
     * Example usage of the XmlCredentialReader
     */
    public static void main(String[] args) {
        // Example usage
        List<Map<String, String>> users = readCredentials("credentials.xml");
        
        // Print all users
        for (Map<String, String> user : users) {
            System.out.println("Username: " + user.get("username"));
            System.out.println("Password: " + user.get("password"));
            System.out.println("Email: " + user.get("email"));
            System.out.println("-------------------");
        }
    }
}