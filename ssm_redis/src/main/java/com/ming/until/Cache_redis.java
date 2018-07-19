package com.ming.until;

import redis.clients.jedis.Jedis;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class Cache_redis   {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("q","1");
        System.out.println(jedis.get("q"));




        /*byte[] byt=jedis.get("emp_6".getBytes());
        Object obj=unserizlize(byt);
        if(obj instanceof EmpEntity){
            EmpEntity emp = (EmpEntity) obj;
            System.out.println(emp.toString());
        }*/
    }
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }

}
