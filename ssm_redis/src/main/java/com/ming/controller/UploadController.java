package com.ming.controller;

import com.ming.until.ExcelUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class UploadController {

    //上传
    @ResponseBody
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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
            File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
            // 新文件
            File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
            // 判断目标文件所在目录是否存在
            if (!newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }
            System.out.println(newFile);
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            // 完整的url
            String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //下載
    @ResponseBody
    @GetMapping("do_download")
    public void downFile(HttpServletResponse response, @Param("fileUrl") String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String file = url.getFile();
            String last=file.substring(file.lastIndexOf("."));
            //打开网络输入流
            InputStream is = url.openStream();
            // 清空response
            response.reset();
            String enFileName = URLEncoder.encode("cc"+last, "utf-8");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            // 设置response的Header
            ExcelUtil.setResponseHeader(response,enFileName);
            byte[] buffer = new byte[1024];
            //开始填充数据
            while (true) {
                int readed = is.read(buffer);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buffer, 0, temp, 0, readed);
                toClient.write(temp);
            }
            toClient.flush();
            is.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}