package com.ming.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.entity.DempEntity;
import com.ming.entity.EmpEntity;
import com.ming.service.Dempservice;
import com.ming.service.Empservice;
import com.ming.until.ExcelUtil;
import com.ming.until.Msg;
import com.ming.until.SixEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Controller
public class EmpController {

    @Autowired
    Empservice empservice;
    @Autowired
    Dempservice dempservice;


    @RequestMapping("/emplist")
    public String emplist(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        try {
            //Testclass.addtest(empservice);
            //empservice.insertemp(null);
            // ÿҳ��ʾ5��
            PageHelper.startPage(page, 8);
            List<EmpEntity> emps = empservice.selectall();
            for (EmpEntity e : emps) {
                e.setGender(SixEnum.getSix(e.getGender() == null ? "" : e.getGender()).toString());
            }
            // �Խ�����а�װ ������ʾҳ��5ҳ ����34567
            PageInfo pi = new PageInfo(emps, 5);
            System.out.println(pi.toString());
            model.addAttribute("pi", pi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "emplist";
    }

    @ResponseBody
    @RequestMapping("/demp")
    public List<DempEntity> seldemp() {
        List<DempEntity> demps = new ArrayList<DempEntity>();
        try {
            System.out.println("��ʼ");
            demps = dempservice.selDemp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return demps;
    }

    @ResponseBody
    @RequestMapping("/addemp")
    public Msg adddept(EmpEntity emp) {
        int i = 0;
        try {
            i = empservice.insertemp(emp);
            if (i > 0) {
                return Msg.success().add("msg", "SUCCESS");
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/editemp")
    public Msg editemp(EmpEntity emp) {
        try {
            EmpEntity e = empservice.editemp(emp);
            if (e != null) {
                return Msg.success().add("msg", "SUCCESS");
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/delemp")
    public Msg delemp(@RequestParam("id") int id) {
        int i = empservice.delemp(id);
        if (i > 0) {
            return Msg.success().add("msg", "SUCCESS");
        } else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/selemp")
    public EmpEntity selemp(EmpEntity emparm) {
        try {
            EmpEntity emp = empservice.selemp(emparm);
            return emp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/excle")
    public void exclout(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<EmpEntity> list = empservice.selectall();
            //excel标题
            String[] title = {"名称", "性别", "年龄", "学校"};

            //excel文件名
            String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

            //sheet名
            String sheetName = "学生信息表";
            String[][] content = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                EmpEntity obj = list.get(i);
                content[i][0] = obj.getName();
                content[i][1] = obj.getEmail();
                content[i][2] = obj.getGender();
                content[i][3] = obj.getdName();
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null,null);
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

    @ResponseBody
    @RequestMapping("/excleIn")
    public void excleIn(@Param("file")MultipartFile file, HttpServletRequest request)throws Exception{
        List<List<Object>> listob = null;
        InputStream in =null;
        if(file.isEmpty()){
            throw new Exception("文件不存在");
        }
        in = file.getInputStream();
        listob = ExcelUtil.setHSSFWorkbook(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            EmpEntity vo = new EmpEntity();
            vo.setName(String.valueOf(lo.get(1)));

            System.out.println(vo.toString());
        }
    }

    @RequestMapping("/sheet")
    public String sheet(){
        return "excel";
    }




}
