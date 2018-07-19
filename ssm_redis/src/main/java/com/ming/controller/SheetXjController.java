package com.ming.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ming.entity.ChangCause;
import com.ming.entity.SheetEntity;
import com.ming.service.Sheetservice;
import com.ming.until.ExcelUtil;
import com.ming.until.Msg;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 现金
 * */
@Controller
@RequestMapping("/sheetxj")
public class SheetXjController {

    @Autowired
    Sheetservice sheetservice;

    /**
     * 初始页
     */
    @RequestMapping("/sheetinit")
    public String sheetinit(Model model) {
        Calendar calendar = Calendar.getInstance();
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        String year = calendar.get(Calendar.YEAR) + "";
        model.addAttribute("month",month);
        model.addAttribute("year",year);
        return "excel_xj";
    }

    /*查询*/
    @RequestMapping("/sheet")
    public String sheet(Model model, String company, String time, String month) {
        try {
            SheetEntity sheet = new SheetEntity();
            Calendar calendar = Calendar.getInstance();
            if (StringUtils.isEmpty(month)) {
                month = calendar.get(Calendar.MONTH) + 1 + "";
            }
            if (StringUtils.isEmpty(time)) {
                time = calendar.get(Calendar.YEAR) + "";
            }
            sheet.setCorpName(company);
            sheet.setYears(time);
            List<SheetEntity> sheetEntities = sheetservice.selsheet01_xj(null);
            sheet.setYears(Integer.parseInt(time)-1+"");
            List<SheetEntity> sheetEntities1 = sheetservice.selsheet01_lr(sheet);
            if(sheetEntities1.size()>0){
                for (int i=0;i<sheetEntities.size();i++){
                    sheetEntities.get(i).setLastyears(sheetEntities1.get(i).getlastyears(month));
                }
            }
            model.addAttribute("month", month);
            model.addAttribute("sheets", sheetEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "excel_xjinfo";
    }

    public ChangCause becaseval(int id) {
        ChangCause changCause = new ChangCause();
        try {
            SheetEntity sheet = new SheetEntity();
            sheet.setId(id);
            List<SheetEntity> sheetEntities = sheetservice.selsheet01_xj(sheet);
            sheet = sheetEntities.size() > 0 ? sheetEntities.get(0) : null;
            if (sheet != null && !StringUtils.isEmpty(sheet.getChangeCause())) {
                changCause = JSON.parseObject(sheet.getChangeCause(), ChangCause.class);
            } else {
                changCause = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return changCause;
    }

    /**
     * 查询分析
     */
    @ResponseBody
    @RequestMapping("/findanalyze")
    public Msg findanalyze(String month, int id) {
        try {
            ChangCause changCause = becaseval(id);
            if (StringUtils.isEmpty(changCause)) {
                return Msg.success().add("val", "");
            } else {
                return Msg.success().add("val", changCause.getval(month));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    /**
     * 修改分析
     */
    @ResponseBody
    @RequestMapping("/editanalyze")
    public Msg editanalyze(String month, int id, String val) {
        try {
            ChangCause changCause = becaseval(id);
            if (!StringUtils.isEmpty(changCause)) {
                changCause.setval(month, val);
            } else {
                changCause = new ChangCause();
                changCause.setval(month, val);
            }
            SheetEntity sheet = new SheetEntity();
            String s = JSONObject.toJSONString(changCause);
            sheet.setChangeCause(s);
            sheet.setId(id);
            sheetservice.editsheet_xj(sheet);
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    /*导入*/
    @RequestMapping("/sheet1")
    public String sheet1(@Param("file") MultipartFile file, String company, String time, String month, HttpServletRequest request) {
        try {
            SheetEntity sheet = new SheetEntity();
            List<SheetEntity> listob = null;
            sheet.setCorpName(company);
            int i = sheetservice.selsheet01_xj(sheet).size();
            InputStream in = null;
            if (file.isEmpty()) {
                throw new Exception("文件不存在");
            }
            in = file.getInputStream();
            listob = ExcelUtil.sheet03(in, file.getOriginalFilename(), company, time, month);
            in.close();
            if (i > 0) {
                for (SheetEntity li : listob) {
                    sheetservice.editsheet_xj(li);
                }
            } else {
                sheetservice.inserts_xj(listob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/sheetxj/sheetinit";
    }

    /*导出*/
    @ResponseBody
    @RequestMapping("/sheetout")
    public void sheetout(String company, String time, String month, HttpServletRequest request, HttpServletResponse response) {
        try {
            Map map = new HashMap();
            map.put("company", company);
            map.put("title", "现金流量表");
            month = Integer.parseInt(month) + "";
            SheetEntity sheet = new SheetEntity();
            sheet.setCorpName(company);
            sheet.setYears(time);
            List<SheetEntity> list = sheetservice.selsheet01_xj(sheet);
            sheet.setYears(Integer.parseInt(time)-1+"");
            List<SheetEntity> sheetEntities1 = sheetservice.selsheet01_lr(sheet);
            if(sheetEntities1.size()>0){
                for (int i=0;i<list.size();i++){
                    list.get(i).setLastyears(sheetEntities1.get(i).getlastyears(month));
                }
            }
            if (list.size() > 0) {
                map.put("unit", list.get(0).getUnit());
            }
            //excel标题
            String[] title = {"项目", "行次", "2018-1-31", "2018-2-28", "2018-3-31", "2018-4-30", "2018-5-31", "2018-6-30", "2018-7-31", "2018-8-31", "2018-9-30", "2018-10-31", "2018-11-30", "2018-12-31", "本年累计", "上年同期累计","环比上月增长率","同比增长率", "变动原因解析"};

            //excel文件名
            String fileName = "现金流量表.xls";

            //sheet名
            String sheetName = "现金流量表";
            Object[][] content = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                SheetEntity obj = list.get(i);
                content[i][0] = obj.getSubjectName();
                content[i][1] = (i + 1) + "";
                content[i][2] = obj.getMonth01() + "";
                content[i][3] = obj.getMonth02() + "";
                content[i][4] = obj.getMonth03() + "";
                content[i][5] = obj.getMonth04() + "";
                content[i][6] = obj.getMonth05() + "";
                content[i][7] = obj.getMonth06() + "";
                content[i][8] = obj.getMonth07() + "";
                content[i][9] = obj.getMonth08() + "";
                content[i][10] = obj.getMonth09() + "";
                content[i][11] = obj.getMonth10() + "";
                content[i][12] = obj.getMonth11() + "";
                content[i][13] = obj.getMonth12() + "";
                content[i][14] = obj.getYearTotal() + "";
                content[i][15] = obj.getLastyears() + "";
                content[i][16] = ((obj.getval(month) -obj.getval(Integer.parseInt(month)-1+""))/ obj.getval(Integer.parseInt(month)-1+"")==0?1:obj.getval(Integer.parseInt(month)-1+"")) +"";
                content[i][17] = ((obj.getYearTotal()-obj.getLastyears())/obj.getLastyears()==0?1:obj.getLastyears())+"";
                ChangCause changCause=JSON.parseObject(obj.getChangeCause(),ChangCause.class);
                String c=changCause==null?"":changCause.getval(month);
                content[i][18] = c;
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null, map);
            //响应到客户端
            ExcelUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
