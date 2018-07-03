package com.ming.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExeclTest {
    @Test
    public void writeExcel03() throws IOException {
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        HSSFSheet sheet = workBook.createSheet("helloWorld");
        for(int i=0;i<3;i++){//创建行数
            //创建行,第3行
            HSSFRow row = sheet.createRow(i);
            System.out.println("i:"+i);
            for (int j=0;j<3;j++){//创建列数
                System.out.println("j:"+j);
                //创建单元格，操作第三行第三列
                HSSFCell cell = row.createCell(j, CellType.STRING);
                cell.setCellValue("helloWorld");
            }

        }


        workBook.write(new File("d:\\测试.xls"));

        workBook.close();//最后记得关闭工作簿
    }
}
