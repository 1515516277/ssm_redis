package com.ming.test;

import com.ming.entity.SheetEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String[][] strings = new String[1][];

    }

    @org.junit.Test
    public void Excelout() throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(new File("d://导入模板20180706.xls"));
            //读取工作簿
            HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
            //读取工作表
            HSSFSheet sheet = workBook.getSheetAt(3);
            //读取行
            HSSFRow row = sheet.getRow(15);
            //读取单元格
            HSSFCell cell = row.getCell(1);
            //Object value = cell.getStringCellValue();
            String value = getCellValue(cell)+"";
            //SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy");
            //SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");

            try {
                System.out.println(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            inputStream.close();
            workBook.close();//最后记得关闭工作簿
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void str() {
        // 循环行Row
        List<SheetEntity> sheets = new ArrayList<>();
        List<SheetEntity> sheets1 = new ArrayList<>();
        try {
            InputStream is = new FileInputStream("d:\\导入模板20180706.xls");
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 3; numSheet < 4; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    SheetEntity sheet = new SheetEntity();// 新建一个user对象
                    SheetEntity sheet1 = new SheetEntity();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 3 && rowNum <= 35) {
                        sheet.setYears("2018");
                        sheet.setCorpName("xxxx");//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(5)).toString());//单位
                        sheet.setSubjectName(getCellValue(hssfRow.getCell(0)).toString());//名称
                        sheet.ucc("1", (getCellValue(hssfRow.getCell(1)).equals("") ? 0 :  Double.valueOf(getCellValue(hssfRow.getCell(1)).toString())));//时间
                        sheet.setBeginYearBalance( getCellValue(hssfRow.getCell(2)).equals("") ? 0 :  Double.valueOf(getCellValue(hssfRow.getCell(2)).toString()));//年
                        sheet1.setYears("2018");
                        sheet1.setCorpName("xxxx");
                        sheet1.setUnit(getCellValue(hssfSheet.getRow(1).getCell(5)).toString());
                        sheet1.setSubjectName(getCellValue(hssfRow.getCell(3)).toString());
                        sheet1.ucc("1",  (getCellValue(hssfRow.getCell(4)).equals("") ? 0 :  Double.valueOf(getCellValue(hssfRow.getCell(4)).toString())));
                        sheet1.setBeginYearBalance( getCellValue(hssfRow.getCell(5)).equals("") ? 0 :  Double.valueOf(getCellValue(hssfRow.getCell(5)).toString()));
                        sheets.add(sheet);
                        sheets1.add(sheet);
                    }

                    System.out.println();
                }
                sheets.addAll(sheets1);
                System.out.println(sheets.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //获取单元格各类型值，返回字符串类型
    private static String getCellValueByCell(HSSFCell cell) {
        //判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
        cellValue = hSSFDataFormatter.formatCellValue(cell); // 使用EXCEL原来格式的方式取得值
        return cellValue;
    }

    public Object getCellValue(Cell cell) {
        Object value = "";
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                if (value.toString().equals("null")) {
                    value = "";
                }
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                try {
                    value = cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                break;
        }
        return value;
    }


    @org.junit.Test
    public void sublis() {
        Object c="1.1";
        //Double b= Double.valueOf(c.toString());
        Double b=null;
        //System.out.println(b);

        Map map=new HashMap();
        System.out.println(map.get("cc"));

    }

    @org.junit.Test
    public void ato1(){
        //letterToNum("10");
        //System.out.println("10".hashCode());
        System.out.println(null+"");
        String s = " ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        //System.out.println(s.charAt(1));
        //System.out.print((char) ("2".hashCode() + 48));
    }

    public void letterToNum(String input) {
        for (byte b : input.getBytes()) {
            System.out.print((char) (b + 48));
        }

    }
}
