package com.boot;

import com.boot.util.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class demonstrates how to use the ExcelReader utility
 * to read data from Excel files using HashMap instead of NodeList.
 */
public class ExcelDemo {

    public static void main(String[] args) {
        System.out.println("Excel Reader Demo - Using HashMap instead of NodeList");
        System.out.println("====================================================");
        
        // Example file path - replace with your actual file path
        String excelFilePath = "sample.xlsx";
        
        try {
            // Example 1: Reading all data from an Excel sheet
            System.out.println("\nExample 1: Reading all data from an Excel sheet");
            System.out.println("---------------------------------------------");
            readAllData(excelFilePath);
            
            // Example 2: Reading specific cells
            System.out.println("\nExample 2: Reading specific cells");
            System.out.println("--------------------------------");
            readSpecificCells(excelFilePath);
            
            // Example 3: Accessing data by column name
            System.out.println("\nExample 3: Accessing data by column name");
            System.out.println("-------------------------------------");
            accessByColumnName(excelFilePath);
            
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            System.err.println("Make sure the file exists and is accessible.");
            System.err.println("You can create a sample.xlsx file or modify the code to use your own Excel file.");
            e.printStackTrace();
        }
    }
    
    /**
     * Example of reading all data from an Excel sheet
     */
    private static void readAllData(String filePath) throws IOException {
        // Read all data from the first sheet, assuming the first row contains headers
        List<Map<String, String>> data = ExcelReader.readExcelData(filePath, null, true);
        
        System.out.println("Total rows read: " + data.size());
        
        // Print the data
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            System.out.println("Row " + (i + 1) + ":");
            
            for (Map.Entry<String, String> entry : row.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
            
            System.out.println();
        }
    }
    
    /**
     * Example of reading specific cells from an Excel sheet
     */
    private static void readSpecificCells(String filePath) throws IOException {
        // Read specific cells by row and column index (0-based)
        String cell1 = ExcelReader.readCellValue(filePath, null, 0, 0);
        String cell2 = ExcelReader.readCellValue(filePath, null, 0, 1);
        String cell3 = ExcelReader.readCellValue(filePath, null, 1, 0);
        String cell4 = ExcelReader.readCellValue(filePath, null, 1, 1);
        
        System.out.println("Cell at row 0, column 0: " + cell1);
        System.out.println("Cell at row 0, column 1: " + cell2);
        System.out.println("Cell at row 1, column 0: " + cell3);
        System.out.println("Cell at row 1, column 1: " + cell4);
    }
    
    /**
     * Example of accessing data by column name
     */
    private static void accessByColumnName(String filePath) throws IOException {
        // Read all data from the first sheet, assuming the first row contains headers
        List<Map<String, String>> data = ExcelReader.readExcelData(filePath, null, true);
        
        if (data.isEmpty()) {
            System.out.println("No data found in the Excel file.");
            return;
        }
        
        // Get the column names from the first row
        Map<String, String> firstRow = data.get(0);
        System.out.println("Available columns: " + String.join(", ", firstRow.keySet()));
        
        // Access data by column name for each row
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            System.out.println("Row " + (i + 1) + ":");
            
            // Example: If your Excel has columns like "Name", "Age", "Email"
            // You can access them directly by name
            for (String columnName : row.keySet()) {
                String value = row.get(columnName);
                System.out.println("  " + columnName + ": " + value);
            }
            
            System.out.println();
        }
    }
}