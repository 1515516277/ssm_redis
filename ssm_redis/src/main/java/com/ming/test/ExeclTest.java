package com.ming.test;

import com.ming.entity.SheetEntity;
import com.ming.service.Sheetservice;
import com.ming.until.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                cell.setCellValue(i+1+j);
            }

        }
        HSSFRow row = sheet.createRow(4);
        HSSFCell cell = row.createCell(1);
        String tx="sum(a1:a2)";
        cell.setCellFormula(tx);


        workBook.write(new File("d:\\测试.xls"));

        workBook.close();//最后记得关闭工作簿
    }

    @Autowired
    Sheetservice sheetservice;
    @Test
    public void test(){
        try {
            String company="xxx";
            String time="2018";
            String month="2";
            Map map=new HashMap();
            map.put("company",company);
            map.put("title","资产负债表");
            month=Integer.parseInt(month)+"";
            SheetEntity sheet=new SheetEntity();
            sheet.setCorpName(company);
            sheet.setYears(time);
            List<SheetEntity> list = sheetservice.selsheet01(sheet);
            if(list.size()>0){
                map.put("unit",list.get(0).getUnit());
            }
            //excel标题
            String[] title = {"项目", "行次", "年初余额", "2018-1-31", "2018-2-28", "2018-3-31", "2018-4-30", "2018-5-31", "2018-6-30", "2018-7-31", "2018-8-31", "2018-9-30", "2018-10-31", "2018-11-30", "2018-12-31", "与上月变动额", "与年初变动额", "变动原因解析"};

            //excel文件名
            String fileName = "资产负债.xls";

            //sheet名
            String sheetName = "资产负债表";
            Object[][] content = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                SheetEntity obj = list.get(i);
                content[i][0] = obj.getSubjectName();
                content[i][1] = (i+1)+"";
                content[i][2] = obj.getBeginYearBalance()+"";
                content[i][3] = obj.getMonth01()+"";
                content[i][4] = obj.getMonth02()+"";
                content[i][5] = obj.getMonth03()+"";
                content[i][6] = obj.getMonth04()+"";
                content[i][7] = obj.getMonth05()+"";
                content[i][8] = obj.getMonth06()+"";
                content[i][9] = obj.getMonth07()+"";
                content[i][10] = obj.getMonth08()+"";
                content[i][11] = obj.getMonth09()+"";
                content[i][12] = obj.getMonth10()+"";
                content[i][13] = obj.getMonth11()+"";
                content[i][14] = obj.getMonth12()+"";
                if(month.equals("1")){
                    content[i][15] =  obj.getval(month)+"";
                }else{
                    content[i][15] =  obj.getval(month)-obj.getval((Integer.parseInt(month)-1)+"")+"";
                }
                content[i][16] = obj.getval(month)-obj.getBeginYearBalance()+"";
                content[i][17] = obj.getChangeCause();
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null,map);
            wb.write(new File("d:\\测试.xls"));
            wb.close();//最后记得关闭工作簿
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
