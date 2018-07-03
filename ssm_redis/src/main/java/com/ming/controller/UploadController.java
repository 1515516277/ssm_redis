package com.ming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class UploadController {

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            file.getOriginalFilename();
            // uploads文件夹位置
            String rootPath = request.getSession().getServletContext().getRealPath("resources/uploads/");
            // 原始名称
            String originalFileName = file.getOriginalFilename();
            // 新文件名
            String newFileName = "sliver" + res + originalFileName.substring(originalFileName.lastIndexOf("."));
            // 创建年月文件夹
            Calendar date = Calendar.getInstance();
            File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));
           // 新文件
            File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
            // 判断目标文件所在目录是否存在
            if( !newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }
            System.out.println(newFile);
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            // 完整的url
            String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + newFileName;
            return  fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
