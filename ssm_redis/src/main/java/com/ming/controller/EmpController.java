package com.ming.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.entity.DempEntity;
import com.ming.entity.EmpEntity;
import com.ming.service.Dempservice;
import com.ming.service.Empservice;
import com.ming.until.Msg;
import com.ming.until.SixEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        int i=0;
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
           EmpEntity emp= empservice.selemp(emparm);
            return emp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/t")
    public Msg t() {
        if (2 > 0) {
            return Msg.success().add("msg", "SUCCESS");
        } else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @RequestMapping("/te")
    public Msg te() {
        String c = "";
        try {
            if (Integer.parseInt(c) < 2) {
                String b = null;
                b.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    @ResponseBody
    @RequestMapping("/tt")
    public Msg tt(@RequestBody JSONObject jsonObject) {
        try {
            if (2 > 0) {
                return Msg.success().add("msg", jsonObject.getString("sour"));
            } else {
                return Msg.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

}
