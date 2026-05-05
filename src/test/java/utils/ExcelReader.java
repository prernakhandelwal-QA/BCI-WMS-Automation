package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] getLoginData(String fileName, String sheetName) {

        List<Object[]> data = new ArrayList<>();

        try {
            InputStream input = ExcelReader.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            Workbook workbook = WorkbookFactory.create(input);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) { // skip header
                Row row = sheet.getRow(i);

                String username = row.getCell(1).toString().trim();
                String password = row.getCell(2).toString().trim();
                String expectedResult = row.getCell(3).toString().trim();

                data.add(new Object[]{username, password, expectedResult});
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }
}