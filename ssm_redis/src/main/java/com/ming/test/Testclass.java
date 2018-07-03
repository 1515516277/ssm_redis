package com.ming.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.ming.entity.EmpEntity;
import com.ming.service.Empservice;
import org.junit.Test;

public class Testclass {


    public static void addtest(Empservice empservice) {
        EmpEntity emp = new EmpEntity();
        System.out.println("��ʼ");
        for (int i = 0; i < 1000; i++) {
            emp.setName(UUID.randomUUID().toString().substring(0, 4));
            emp.setEmail("www.123");
            if (i % 2 == 0) {
                emp.setGender("1");
            } else {
                emp.setGender("0");
            }
            emp.setdId(1);
            empservice.insertemp(emp);
        }
        System.out.println("����");
    }

    public static void jsonxml() {
        String json = "";
    }


    @Test
    public void t2() {
        Object[] obj = new Object[2];
        System.out.println(Arrays.asList(obj));
        obj[0] = 1;
        obj[1] = 2;
        System.out.println(Arrays.asList(obj));
    }

    @Test
    public void getcontext() throws IOException {
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);
    }

    @Test
    public void download() {
        try {
            URL url = new URL("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3569479631,993103025");
            File outFile = new File("d://c.jpg");
            OutputStream os = new FileOutputStream(outFile);
            InputStream is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                os.write(temp);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
