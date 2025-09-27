package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Xlsutility {

    String path;
    public   FileInputStream fi;
    public FileOutputStream fo;
    public XSSFSheet sheet;
    public XSSFWorkbook workBook;
    public XSSFRow row;
    public XSSFCell cell;



    public Xlsutility(String path)
    {
        this.path = path;
    }

    public int getRowCount(String sheetname) throws IOException {
        fi = new FileInputStream(path);
     //   FileOutputStream fo = new FileOutputStream(path);
        workBook = new XSSFWorkbook(fi);
         sheet = workBook.getSheet(sheetname);
         int count = sheet.getLastRowNum();
         workBook.close();
         fi.close();
         return count;
    }

    public int getCellCount(String sheetname,int rowNum) throws IOException {
        fi = new FileInputStream(path);
        //   FileOutputStream fo = new FileOutputStream(path);
        workBook = new XSSFWorkbook(fi);
        sheet = workBook.getSheet(sheetname);
        XSSFRow row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workBook.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetname,int rowNum,int colnum) throws IOException {

        fi = new FileInputStream(path);
        workBook = new XSSFWorkbook(fi);
        sheet = workBook.getSheet(sheetname);
         row = sheet.getRow(rowNum);
         cell = row.getCell(colnum);

        DataFormatter df = new DataFormatter();
        String data;
        try {
            data = df.formatCellValue(cell);
        }
        catch (Exception ex)
        {
            data = "" ;
        }
        return data;

    }
}
