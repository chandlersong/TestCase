package com.hilatest.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

public class BasicTest {

    @Test
    public void createCell() throws IOException {
        File outputFile = new File(SystemUtils.getJavaIoTmpDir(), "workbook.xls");
        Workbook wb = new HSSFWorkbook();
        // Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file

        System.out.println(outputFile.getAbsolutePath());
        FileOutputStream fileOut = new FileOutputStream(outputFile);
        wb.write(fileOut);
        fileOut.close();

    }

    @Test
    public void createReadAndWrite() throws IOException, InvalidFormatException {
        File outputFile = new File(SystemUtils.getJavaIoTmpDir(), "workbook.xls");
        System.out.println(outputFile.getAbsolutePath());
        Workbook wb = new HSSFWorkbook();
        // Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(outputFile);
        wb.write(fileOut);
        fileOut.close();

        InputStream inp = new FileInputStream(outputFile);
        Workbook wbNext = WorkbookFactory.create(inp);
        Sheet sheetNext = wbNext.getSheet("new sheet");

        Row row2 = sheetNext.createRow(sheetNext.getLastRowNum() + 1);
        // Or do it on one line.
        row2.createCell(1).setCellValue(21313);
        row2.createCell(2).setCellValue(
                createHelper.createRichTextString("This is a second row"));
        row2.createCell(3).setCellValue(false);

        // Write the output to a file
        FileOutputStream fileOutNext = new FileOutputStream(outputFile);
        wbNext.write(fileOutNext);
        fileOutNext.close();
    }
}
