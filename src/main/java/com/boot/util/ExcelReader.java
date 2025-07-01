package com.boot.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class to read data from Excel files (both .xls and .xlsx formats)
 * using HashMap instead of NodeList for simpler data access.
 */
public class ExcelReader {

    /**
     * Reads data from an Excel file and returns it as a list of maps,
     * where each map represents a row with column names as keys.
     *
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet to read (if null, reads the first sheet)
     * @param hasHeaderRow Whether the first row contains headers
     * @return List of maps, each representing a row of data
     * @throws IOException If there's an error reading the file
     */
    public static List<Map<String, String>> readExcelData(String filePath, String sheetName, boolean hasHeaderRow) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = getWorkbook(fis, filePath)) {
            
            // Get the specified sheet or the first sheet if not specified
            Sheet sheet = (sheetName != null && !sheetName.isEmpty()) 
                ? workbook.getSheet(sheetName) 
                : workbook.getSheetAt(0);
            
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            
            // Get column headers if the first row contains headers
            String[] headers = null;
            int startRow = 0;
            
            if (hasHeaderRow) {
                Row headerRow = sheet.getRow(0);
                if (headerRow != null) {
                    headers = new String[headerRow.getLastCellNum()];
                    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                        Cell cell = headerRow.getCell(i);
                        headers[i] = (cell != null) ? getCellValueAsString(cell) : "Column" + (i + 1);
                    }
                    startRow = 1;
                }
            }
            
            // Read data rows
            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                Map<String, String> rowData = new HashMap<>();
                
                for (int j = 0; j < (headers != null ? headers.length : row.getLastCellNum()); j++) {
                    Cell cell = row.getCell(j);
                    String value = (cell != null) ? getCellValueAsString(cell) : "";
                    
                    // Use header name as key if available, otherwise use column index
                    String key = (headers != null) ? headers[j] : "Column" + (j + 1);
                    rowData.put(key, value);
                }
                
                data.add(rowData);
            }
        }
        
        return data;
    }
    
    /**
     * Reads a specific cell value from an Excel file.
     *
     * @param filePath Path to the Excel file
     * @param sheetName Name of the sheet (if null, uses the first sheet)
     * @param rowIndex Row index (0-based)
     * @param columnIndex Column index (0-based)
     * @return The cell value as a string
     * @throws IOException If there's an error reading the file
     */
    public static String readCellValue(String filePath, String sheetName, int rowIndex, int columnIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = getWorkbook(fis, filePath)) {
            
            Sheet sheet = (sheetName != null && !sheetName.isEmpty()) 
                ? workbook.getSheet(sheetName) 
                : workbook.getSheetAt(0);
            
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                return "";
            }
            
            Cell cell = row.getCell(columnIndex);
            return (cell != null) ? getCellValueAsString(cell) : "";
        }
    }
    
    /**
     * Creates a workbook based on the file extension (.xls or .xlsx).
     *
     * @param fis FileInputStream for the Excel file
     * @param filePath Path to the Excel file
     * @return HSSFWorkbook for .xls files or XSSFWorkbook for .xlsx files
     * @throws IOException If there's an error creating the workbook
     */
    private static Workbook getWorkbook(FileInputStream fis, String filePath) throws IOException {
        if (filePath.toLowerCase().endsWith(".xlsx")) {
            return new XSSFWorkbook(fis);
        } else if (filePath.toLowerCase().endsWith(".xls")) {
            return new HSSFWorkbook(fis);
        } else {
            throw new IllegalArgumentException("Not an Excel file: " + filePath);
        }
    }
    
    /**
     * Converts a cell value to a string, handling different cell types.
     *
     * @param cell The cell to read
     * @return The cell value as a string
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Avoid scientific notation and trailing zeros for numeric values
                    double value = cell.getNumericCellValue();
                    if (value == Math.floor(value)) {
                        return String.format("%.0f", value);
                    } else {
                        return String.valueOf(value);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    try {
                        return cell.getStringCellValue();
                    } catch (IllegalStateException e2) {
                        return cell.getCellFormula();
                    }
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }
    
    /**
     * Example usage of the ExcelReader
     */
    public static void main(String[] args) {
        try {
            // Example: Read all data from an Excel file
            String excelFilePath = "sample.xlsx";
            List<Map<String, String>> data = readExcelData(excelFilePath, null, true);
            
            System.out.println("Excel Data:");
            for (Map<String, String> row : data) {
                for (Map.Entry<String, String> entry : row.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("-------------------");
            }
            
            // Example: Read a specific cell
            String cellValue = readCellValue(excelFilePath, null, 1, 1);
            System.out.println("Cell value at row 1, column 1: " + cellValue);
            
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}