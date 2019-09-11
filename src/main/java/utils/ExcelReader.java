package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class ExcelReader{

    public static Object[][] getTestData(String methodName) throws IOException {
        int lastCellNum, index = 0;

        File file = new File(System.getProperty("user.dir") + "/src/main/resources/"+RequestMethods.loadConfigProperties().getProperty("TestDataSheet"));
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(RequestMethods.loadConfigProperties().getProperty("workSheetName"));

        int lastRowNum = sheet.getLastRowNum();
        int countRowsInTestData=getRowCount(lastRowNum, sheet, methodName);
        Object[][] obj = new Object[countRowsInTestData][1];

        for (int rows = 0; rows < lastRowNum; rows++) {

            if (sheet.getRow(rows) != null) {
                if (sheet.getRow(rows).getCell(0).getStringCellValue().equals(methodName)) {

                    int startRowNum = rows;
                    for (int row = startRowNum; row < lastRowNum; row++) {

                        while (sheet.getRow(row + 2) != null) {

                            if (row != lastRowNum) {
                                lastCellNum = sheet.getRow(row + 1).getLastCellNum();
                            } else {
                                lastCellNum = sheet.getRow(row).getLastCellNum();
                            }

                            Map<Object, Object> datamap = new HashMap<>();
                            for (int j = 0; j < lastCellNum; j++) {
                                datamap.put(sheet.getRow(startRowNum + 1).getCell(j).toString(), sheet.getRow(row + 2).getCell(j).toString());
                            }

                            obj[index][0] = datamap;
                            index++;
                            row++;
                        }

                        break;
                    }
                }
            }
        }

        return obj;
    }

    public static int getRowCount(int lastRowNum, XSSFSheet sheet, String methodName) {
        int count = 0;
        for (int rowNum = 0; rowNum < lastRowNum; rowNum++) {
            if (sheet.getRow(rowNum) != null) {
                if (sheet.getRow(rowNum).getCell(0).getStringCellValue().equals(methodName)) {
                    int startRowNum = rowNum;

                    for (int row = startRowNum; row < lastRowNum; row++) {
                        while (sheet.getRow(row + 2) != null) {
                            row++;
                            count++;
                        }
                        break;
                    }
                }
            }
        }
        return count;
    }
}