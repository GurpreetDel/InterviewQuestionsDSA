# Excel Reader Utility

This utility provides a simple way to read data from Excel files (both .xls and .xlsx formats) using HashMap instead of NodeList for simpler data access.

## Features

- Read data from Excel files (both .xls and .xlsx formats)
- Access cell values by row and column index
- Access cell values by column name (when using headers)
- Handle different cell types (string, numeric, boolean, formula, etc.)
- Simple HashMap-based data structure for easy access

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Dependencies

The utility uses Apache POI for Excel file manipulation. The following dependencies are required:

```xml
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi</artifactId>
  <version>5.2.3</version>
</dependency>
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>5.2.3</version>
</dependency>
```

These dependencies are already included in the project's pom.xml file.

## Usage

### Creating a Sample Excel File

To test the utility, you can create a simple Excel file with the following steps:

1. Open Microsoft Excel or any other spreadsheet application
2. Create a new workbook
3. Add some data to the first sheet, for example:

   | Name    | Age | Email               |
   |---------|-----|---------------------|
   | John    | 30  | john@example.com    |
   | Alice   | 25  | alice@example.com   |
   | Bob     | 35  | bob@example.com     |

4. Save the file as "sample.xlsx" in the project's root directory

### Running the Demo

The project includes a demo class that shows how to use the ExcelReader utility. To run the demo:

1. Make sure you have created the sample.xlsx file as described above
2. Run the batch file: `run_excel_demo.bat`

Alternatively, you can run the demo using Maven:

```
mvn compile exec:java -Dexec.mainClass="com.boot.ExcelDemo"
```

### Code Examples

#### Reading All Data from an Excel Sheet

```java
// Read all data from the first sheet, assuming the first row contains headers
List<Map<String, String>> data = ExcelReader.readExcelData("sample.xlsx", null, true);

// Access the data
for (Map<String, String> row : data) {
    String name = row.get("Name");
    String age = row.get("Age");
    String email = row.get("Email");
    
    System.out.println("Name: " + name + ", Age: " + age + ", Email: " + email);
}
```

#### Reading Specific Cells

```java
// Read a specific cell by row and column index (0-based)
String cellValue = ExcelReader.readCellValue("sample.xlsx", null, 1, 1);
System.out.println("Cell value at row 1, column 1: " + cellValue);
```

## Advantages of Using HashMap Instead of NodeList

Using HashMap instead of NodeList for reading Excel data offers several advantages:

1. **Simpler Access**: Access data by column name (e.g., `row.get("Name")`) instead of navigating through a node hierarchy
2. **Better Performance**: HashMap provides O(1) access time for retrieving values
3. **More Intuitive**: The code is more readable and easier to understand
4. **Type Safety**: The data structure is more strongly typed
5. **Less Boilerplate**: Requires less code to access and manipulate data

## API Reference

### ExcelReader Class

#### `readExcelData(String filePath, String sheetName, boolean hasHeaderRow)`

Reads data from an Excel file and returns it as a list of maps, where each map represents a row with column names as keys.

- **Parameters**:
  - `filePath`: Path to the Excel file
  - `sheetName`: Name of the sheet to read (if null, reads the first sheet)
  - `hasHeaderRow`: Whether the first row contains headers
- **Returns**: List of maps, each representing a row of data
- **Throws**: IOException if there's an error reading the file

#### `readCellValue(String filePath, String sheetName, int rowIndex, int columnIndex)`

Reads a specific cell value from an Excel file.

- **Parameters**:
  - `filePath`: Path to the Excel file
  - `sheetName`: Name of the sheet (if null, uses the first sheet)
  - `rowIndex`: Row index (0-based)
  - `columnIndex`: Column index (0-based)
- **Returns**: The cell value as a string
- **Throws**: IOException if there's an error reading the file

## License

This project is licensed under the MIT License - see the LICENSE file for details.