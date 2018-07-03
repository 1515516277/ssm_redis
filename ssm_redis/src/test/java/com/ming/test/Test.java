package com.ming.test;

import com.ming.entity.DempEntity;
import com.ming.entity.EmpEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        String[][] strings = new String[1][];

    }

    @org.junit.Test
    public void Excelout() throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(new File("d:\\测试.xls"));
            //读取工作簿
            HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
            //读取工作表
            HSSFSheet sheet = workBook.getSheetAt(0);
            //读取行
            HSSFRow row = sheet.getRow(2);
            //读取单元格
            HSSFCell cell = row.getCell(2);
            //Object value = cell.getStringCellValue();
            String value=getCellValueByCell(cell);
            SimpleDateFormat sf=new SimpleDateFormat("MM/dd/yy");
            SimpleDateFormat sf2=new SimpleDateFormat("yyyy-MM-dd");

            try {
                System.out.println(sf2.format(sf.parse(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(value);
            inputStream.close();
            workBook.close();//最后记得关闭工作簿
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @org.junit.Test
    public void str(){
        EmpEntity user = new EmpEntity();// 新建一个user对象
        DempEntity dept = new DempEntity();// 新建一个dept对象
        try {
            InputStream is = new FileInputStream("d:\\测试.xls");
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }

                    // 循环列Cell
                    // "姓名","密码","性别","爱好","简介","部门did"};
                    user.setName(getCellValue(hssfRow.getCell(0)).toString());
                    /*user.setPwd(getValue(hssfRow.getCell(2)));
                    user.setSex(getValue(hssfRow.getCell(3)));
                    user.setHobby(getValue(hssfRow.getCell(4)));
                    user.setJl(getValue(hssfRow.getCell(5)));*/

                    //这里很重要，通过部门列表然后与excel中的部门字段进行对比，匹配后获取对应的did
                    /*String dname = getValue(hssfRow.getCell(6));//获取excel中的部门字段
                    listDept = dbDept.listDept();//得到数据库中的部门列表
                    for (Dept dd : listDept) {//增强for循环
                        if (dd.getDname().equals(dname)) {//如果两者匹配
                            dept.setDid(dd.getDid());//则得到对应的did，并设置dept对象的did
                            user.setDept(dept);//再把dept对象设置到user对象中
                        }
                    }*/
                    System.out.println(user.toString());
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    //获取单元格各类型值，返回字符串类型
    private static String getCellValueByCell(HSSFCell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
        cellValue = hSSFDataFormatter.formatCellValue(cell); // 使用EXCEL原来格式的方式取得值
        return cellValue;
    }
    public  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                value = df.format(cell.getNumericCellValue());
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                value = sdf.format(cell.getDateCellValue());
            }else{
                value = df2.format(cell.getNumericCellValue());
            }
            break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
}
