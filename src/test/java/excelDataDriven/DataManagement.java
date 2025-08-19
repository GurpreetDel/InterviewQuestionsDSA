package excelDataDriven;

public class DataManagement {

    public static void main(String[] args) throws Exception {

        ExcelAPI e = new ExcelAPI("C:\\Users\\ravi\\Desktop\\suitex.xlsx");
        String sheetName = "data";
        String testcaseName = "TestB";

        int testStartRowNum = 0;
        while(!e.getCellData(sheetName, 0, testStartRowNum).equals(testcaseName)) {
            testStartRowNum++;
        }
        System.out.println("Test Start row number :" + testStartRowNum);

    }
}