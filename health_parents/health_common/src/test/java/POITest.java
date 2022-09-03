import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月23日 15:23
 */
public class POITest {
    @Test
    public void readTest() throws IOException, InvalidFormatException {
        // 获取工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(new File("E:\\Java\\code\\传智健康\\poi.xlsx"));
        // 获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 获取行
        for (Row cells : sheet) {
            // 获取单元格
            for (Cell cell : cells) {
                // 获取数据
                System.out.println(cell.getStringCellValue());
            }
        }
    }

    @Test
    public void readTest2() throws IOException, InvalidFormatException {
        // 获取工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(new File("E:\\Java\\code\\传智健康\\poi.xlsx"));
        // 获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 获取最后行号
        int lastRowNum = sheet.getLastRowNum();
        for(int i = 0; i <= lastRowNum; i++){
            XSSFRow row = sheet.getRow(i);
            // 获取最后单元格号
            short lastCellNum = row.getLastCellNum();
            for(int j = 0; j < lastCellNum; j++){
                System.out.println(row.getCell(j).getStringCellValue());
            }
        }
    }

    @Test
    public void writeTest() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("hellocode");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        row.createCell(1).setCellValue("住址");
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("乐友顺");
        row1.createCell(1).setCellValue("安康");

        FileOutputStream out = new FileOutputStream(new File("E:\\Java\\code\\传智健康\\write.xlsx"));
        workbook.write(out);
        out.close();
        workbook.close();
    }
}
