/*
 * Author: Matěj Šťastný aka Kirei
 * Date created: 9/8/2024
 * Github link: https://github.com/kireiiiiiiii/shooting-stars
 */

package kireiiiiiiii.shooting_stars.tools;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadsheetUtil {

    public static String getCellValue(String fileName, String sheetName, int rowNumber, int cellNumber) {
        String cellValue = null;
        String path = File.separator + "spreadsheets" + File.separator + fileName + ".xlsx";
        try (InputStream inputStream = SpreadsheetUtil.class.getResourceAsStream(path); Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return null;
            }
            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                return null;
            }
            Cell cell = row.getCell(cellNumber);

            if (cell != null) {
                cellValue = cell.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellValue;
    }

    public static ArrayList<String> getRowValues(String fileName, String sheetName, int rowNumer) {
        ArrayList<String> row = new ArrayList<String>();
        boolean foundEmpty = false;
        int currCell = 0;
        while (!foundEmpty) {
            String cell = getCellValue(fileName, sheetName, rowNumer, currCell);
            foundEmpty = cell == null;
            if (cell != null) {
                row.add(cell);
            }
            currCell++;
        }
        return row;
    }

    public static ArrayList<String> getColumnValues(String fileName, String sheetName, int columnNumber) {
        ArrayList<String> collum = new ArrayList<String>();
        boolean foundEmpty = false;
        int currCell = 0;
        while (!foundEmpty) {
            String cell = getCellValue(fileName, sheetName, currCell, columnNumber);
            foundEmpty = cell == null;
            if (cell != null) {
                collum.add(cell);
            }
            currCell++;
        }
        return collum;
    }

}
