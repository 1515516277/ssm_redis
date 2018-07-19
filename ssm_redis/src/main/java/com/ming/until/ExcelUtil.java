package com.ming.until;

import com.ming.entity.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, Object[][] values, HSSFWorkbook wb, Map map) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();//大多数使用
        HSSFCellStyle style1 = wb.createCellStyle();//大标题使用
        HSSFCellStyle style2 = wb.createCellStyle();//小标题使用


        HSSFFont font = wb.createFont();//大多数使用
        HSSFFont font1 = wb.createFont();//大标题使用
        HSSFFont font2 = wb.createFont();//小标题使用
        font.setFontName("微软雅黑");
        font1.setFontName("微软雅黑");
        font2.setFontName("微软雅黑");
        style.setFont(font);//大多数
        font2.setBold(true);
        font1.setBold(true);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setFillForegroundColor(IndexedColors.AQUA.index);
        style2.setFont(font2);//小标题
        font1.setFontHeightInPoints((short) 14);//设置字体14
        style1.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        style1.setFont(font1);//大标题
        /*设置宽度*/
        sheet.setDefaultColumnWidth((short) 23);

        // 标题名称
        /*合并单元格*/

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = null;
        cell1 = row1.createCell(0);
        cell1.setCellValue(map.get("title").toString());
        cell1.setCellStyle(style1);

        // 公司  单位 18
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2 = null;
        cell2 = row2.createCell(0);
        cell2.setCellValue("单位名称：" + map.get("company").toString());
        cell2.setCellStyle(style);
        cell2 = row2.createCell(17);
        cell2.setCellValue(map.get("unit").toString());
        cell2.setCellStyle(style);

        HSSFRow row = sheet.createRow(2);


        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style2);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 3);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                Cell cellc = row.createCell(j);
                if(!org.springframework.util.StringUtils.isEmpty(values[i][j] ) ){
                    if(NumberUtils.isNumber(values[i][j] + "" )&& Double.parseDouble(values[i][j] + "") != 0){
                        cellc.setCellValue(Double.parseDouble(values[i][j] + ""));
                    }else if(NumberUtils.isNumber(values[i][j] + "" )&& Double.parseDouble(values[i][j] + "") == 0){
                        cellc.setCellValue("");
                    }else{
                        cellc.setCellValue(values[i][j]+"");
                    }
                }else{
                    cellc.setCellValue("");
                }


                cellc.setCellStyle(style);
            }
        }
        if(!org.springframework.util.StringUtils.isEmpty(map.get("issum")) && map.get("issum").toString().equals("1")){
            String h1=map.get("h1")+"";
            String h2=map.get("h2")+"";
            String s = " ABCDEFGHIGKLMNOPQRSTUVWXYZ";
            row = sheet.createRow( values.length+ 3);
            for (int j = 2; j < values[1].length-3; j++) {
                Cell cellc = row.createCell(j);
                String tx=s.charAt(j+1)+h1;
                String tx2=s.charAt(j+1)+h2;
                String sum=tx+"-"+tx2;
                cellc.setCellFormula(sum);
            }
        }

        return wb;
    }

    /**
     * 导入Excel
     */
    public static List<List<Object>> setHSSFWorkbook(InputStream in, String fileName) throws IOException {
        List<List<Object>> list = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
            //创建Excel工作薄
            if (null == hssfWorkbook) {
                throw new Exception("创建Excel工作薄为空！");
            }
            System.out.println(hssfWorkbook.getNumberOfSheets());
            System.out.println(hssfWorkbook.getSheetAt(0).getLastRowNum());
            System.out.println(hssfWorkbook.getSheetAt(0).getRow(0).getLastCellNum());
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
                    List<Object> li = new ArrayList<>();
                    for (int y = 0; y < hssfRow.getLastCellNum(); y++) {
                        HSSFCell cell = hssfRow.getCell(y);
                        li.add(getCellValue(cell));
                    }
                    list.add(li);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //发送响应流方法
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //获取单元格各类型值，返回字符串类型
    public static Object getCellValue(Cell cell) {
        Object value = "";
        try {
            DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
            DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
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
                    DecimalFormat dft = new DecimalFormat("#");
                    try {
                        value = cell.getStringCellValue();
                    } catch (IllegalStateException e) {
                        value = String.valueOf( dft.format(cell.getNumericCellValue()));
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /*资产负债*/
    public static List<SheetEntity> sheet01(InputStream is, String fileName, String company, String year, String month) {
        // 循环行Row
        List<SheetEntity> sheets = new ArrayList<>();
        List<SheetEntity> sheets1 = new ArrayList<>();
        try {
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
                        sheet.setYears(year);
                        sheet.setCorpName(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(5)).toString());//单位
                        sheet.setSubjectName(getCellValue(hssfRow.getCell(0)).toString());//名称
                        if(rowNum==15){
                            System.out.println(rowNum);
                        }
                        sheet.ucc(month, (getCellValue(hssfRow.getCell(1)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(1)).toString())));//时间
                        sheet.setBeginYearBalance(getCellValue(hssfRow.getCell(2)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(2)).toString()));//年
                        sheet1.setYears(year);
                        sheet1.setCorpName(company);
                        sheet1.setUnit(getCellValue(hssfSheet.getRow(1).getCell(5)).toString());
                        sheet1.setSubjectName(getCellValue(hssfRow.getCell(3)).toString());
                        sheet1.ucc(month, (getCellValue(hssfRow.getCell(4)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(4)).toString())));
                        sheet1.setBeginYearBalance(getCellValue(hssfRow.getCell(5)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(5)).toString()));
                        sheets.add(sheet);
                        sheets1.add(sheet1);
                    }

                    System.out.println();
                }
                sheets.addAll(sheets1);
                System.out.println(sheets.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }
    /*利润*/
    public static List<SheetEntity> sheet02(InputStream is, String fileName, String company, String year, String month) {
        // 循环行Row
        List<SheetEntity> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 4; numSheet < 5; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    SheetEntity sheet = new SheetEntity();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 3 && rowNum <= 21) {
                        sheet.setYears(year);
                        sheet.setCorpName(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(2)).toString());//单位
                        sheet.setSubjectName(getCellValue(hssfRow.getCell(0)).toString());//名称
                        sheet.ucc(month, (getCellValue(hssfRow.getCell(1)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(1)).toString())));//时间
                        sheet.setYearTotal(getCellValue(hssfRow.getCell(2)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(2)).toString()));//年
                        sheets.add(sheet);
                    }

                    System.out.println();
                }
                System.out.println(sheets.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }

    /*现金*/
    public static List<SheetEntity> sheet03(InputStream is, String fileName, String company, String year, String month) {
        // 循环行Row
        List<SheetEntity> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 5; numSheet < 6; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    SheetEntity sheet = new SheetEntity();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 3 && rowNum <= 72) {
                        sheet.setYears(year);
                        sheet.setCorpName(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(2)).toString());//单位
                        sheet.setSubjectName(getCellValue(hssfRow.getCell(0)).toString());//名称
                        sheet.ucc(month, (getCellValue(hssfRow.getCell(1)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(1)).toString())));//时间
                        sheet.setYearTotal(getCellValue(hssfRow.getCell(2)).equals("") ? 0 : Double.valueOf(getCellValue(hssfRow.getCell(2)).toString()));//年
                        sheets.add(sheet);
                    }

                    System.out.println();
                }
                System.out.println(sheets.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }
    /*存货*/
    public static List<InventoryEntity> sheetInventory(InputStream is, String fileName, String company, int year, int month) {
        // 循环行Row
        List<InventoryEntity> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet
            for (int numSheet = 5; numSheet < 6; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    InventoryEntity sheet = new InventoryEntity();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 4 && rowNum <= 27) {
                        sheet.setYears(year);
                        sheet.setMonth(month);
                        sheet.setCompany(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(10)).toString());//单位
                        sheet.setType(getCellValue(hssfRow.getCell(0)).toString());//类别
                        sheet.setName(getCellValue(hssfRow.getCell(1)).toString());//名称规格
                        sheet.setStartNum((Integer) getCellValue(hssfRow.getCell(2)));//期初数量
                        sheet.setStartVal((Double) getCellValue(hssfRow.getCell(3)));//期初金额
                        sheet.setBuyNum((Integer) getCellValue(hssfRow.getCell(4)));//购进数量
                        sheet.setBuyVal((Double) getCellValue(hssfRow.getCell(5)));//购进金额
                        sheet.setStockNum((Integer) getCellValue(hssfRow.getCell(6)));//出库数量
                        sheet.setStockVal((Double) getCellValue(hssfRow.getCell(7)));//出库金额
                        sheet.setBalanceNum((Integer) getCellValue(hssfRow.getCell(8)));//结存数量
                        sheet.setBalanceVal((Double) getCellValue(hssfRow.getCell(9)));//结存金额
                        sheet.setRemark(getCellValue(hssfRow.getCell(10))+"");//备注
                        sheets.add(sheet);
                    }

                    System.out.println();
                }
                System.out.println(sheets.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }


    /*无形资产*/
    public static List<IntangibleAssets> sheetintangibleAssets(InputStream is, String fileName, String company, int year, int month) {
        // 循环行Row
        List<IntangibleAssets> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet

            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(6);

            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                IntangibleAssets sheet = new IntangibleAssets();// 新建一个user对象
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                if (rowNum >= 3 && rowNum <= 31) {
                    sheet.setYears(year);
                    sheet.setMonth(month);
                    sheet.setCompany(company);//公司
                    sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(15)).toString());//单位
                    sheet.setProCode(getCellValue(hssfRow.getCell(0)).toString());//资产编码
                    sheet.setProName(getCellValue(hssfRow.getCell(1)).toString());//资产名称
                    sheet.setMeasureUnit(getCellValue(hssfRow.getCell(2)).toString());//计量单位
                    sheet.setProNum((String) getCellValue(hssfRow.getCell(3)));//资产数量
                    sheet.setDateRecorded((Date) getCellValue(hssfRow.getCell(4)));//日账日期
                    sheet.setWorth((String) getCellValue(hssfRow.getCell(5)));//资产原值
                    sheet.setMakeNum((Integer) getCellValue(hssfRow.getCell(6)));//使用期数
                    sheet.setUndoneExpect((Integer) getCellValue(hssfRow.getCell(7)));//已撤销期数
                    sheet.setExpectRepeal((String) getCellValue(hssfRow.getCell(8)));//每期撤销
                    sheet.setSumRepealVal((String) getCellValue(hssfRow.getCell(9)));//累计撤销金额
                    sheet.setWorth(getCellValue(hssfRow.getCell(10)) + "");//净值
                    sheet.setDept(getCellValue(hssfRow.getCell(11)) + "");//使用部门
                    sheet.setPlace(getCellValue(hssfRow.getCell(12)) + "");//存放地点
                    sheet.setSumVal(getCellValue(hssfRow.getCell(13)) + "");//总金额
                    sheet.setRemake(getCellValue(hssfRow.getCell(14)) + "");//备注
                    sheets.add(sheet);
                }

                System.out.println();
            }
            System.out.println(sheets.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }
    /*长期待费用*/
    public static List<DeferredExpenses> sheetdeferredExpenses(InputStream is, String fileName, String company, int year, int month) {
        // 循环行Row
        List<DeferredExpenses> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet

                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(6);

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    DeferredExpenses sheet = new DeferredExpenses();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 3 && rowNum <= 31) {
                        sheet.setYears(year);
                        sheet.setMonth(month);
                        sheet.setCompany(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(15)).toString());//单位
                        sheet.setContent(getCellValue(hssfRow.getCell(0)).toString());//摊销内容
                        sheet.setSupplier(getCellValue(hssfRow.getCell(1)).toString());//供应商
                        sheet.setPeriod(getCellValue(hssfRow.getCell(2)).toString());//摊销期间
                        sheet.setPeriods((String) getCellValue(hssfRow.getCell(3)));//摊销期数
                        sheet.setUndonePeriods((String) getCellValue(hssfRow.getCell(4)));//已摊销期数
                        sheet.setSumVal((String) getCellValue(hssfRow.getCell(5)));//总金额
                        sheet.setRepealVal((String) getCellValue(hssfRow.getCell(6)));//本期摊销额
                        sheet.setSumRepeal((String) getCellValue(hssfRow.getCell(7)));//累计摊销
                        sheet.setWiteRepealVal((String) getCellValue(hssfRow.getCell(8)));//待摊销余额
                        sheet.setProject((String) getCellValue(hssfRow.getCell(9)));//摊入科目
                        sheet.setRemake(getCellValue(hssfRow.getCell(10))+"");//备注
                        sheets.add(sheet);
                    }

                    System.out.println();
                }
                System.out.println(sheets.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }

    /*实收资本表*/
    public static List<CapitalEntity> sheetCapital(InputStream is, String fileName, String company, int year, int month) {
        // 循环行Row
        List<CapitalEntity> sheets = new ArrayList<>();
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

            // 循环工作表Sheet

                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(6);

                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    CapitalEntity sheet = new CapitalEntity();// 新建一个user对象
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum >= 3 && rowNum <= 31) {
                        sheet.setYears(year);
                        sheet.setMonth(month);
                        sheet.setCompany(company);//公司
                        sheet.setUnit(getCellValue(hssfSheet.getRow(1).getCell(15)).toString());//单位
                        sheet.setInvestor(getCellValue(hssfRow.getCell(0)).toString());//投资方
                        sheet.setPutDate((Date) getCellValue(hssfRow.getCell(1)));//投入日期
                        sheet.setVoucherNumber(getCellValue(hssfRow.getCell(2)).toString());//凭证号
                        sheet.setInvestmentProportion((String) getCellValue(hssfRow.getCell(3)));//投资比例
                        sheet.setVal((String) getCellValue(hssfRow.getCell(4)));//金额
                        sheets.add(sheet);
                    }

                    System.out.println();
                }
                System.out.println(sheets.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheets;
    }
}
