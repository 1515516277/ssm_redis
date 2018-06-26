package com.ming.service.impl;

import com.ming.dao.EmpEntityMapper;
import com.ming.entity.EmpEntity;
import com.ming.service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "redisCacheManager")
public class Empserviceimpl implements Empservice {

    public static final String VAL="redisCacheManager";
    @Autowired
    EmpEntityMapper empmapper;



    public List<EmpEntity> selectall() {
        List<EmpEntity> selectall = empmapper.selectall();
        return selectall;
    }

    @CachePut(value = VAL,key = "'emp_'+#emp.id")
    public int insertemp(EmpEntity emp){

        return empmapper.insertemp(emp);
    }

    public int delemp(int id) {

        return empmapper.delemp(id);
    }

    //查询一条数据
    @Override
    @Cacheable(value = VAL,key = "'emp_'+#emp.id")
    public EmpEntity selemp(EmpEntity emp) {
        EmpEntity selemp = empmapper.selemp(emp);
        return selemp;
    }

    //修改一条数据
    //CachePut key是redis key值  条件可以加这个参数后面跟条件   condition =
    @Override
    @CachePut(value = VAL,key = "'emp_'+#emp.id")
    public EmpEntity editemp(EmpEntity emp) {
         empmapper.editemp(emp);
         EmpEntity selemp = selemp(emp);
        return selemp;
    }

    @CachePut(value = VAL,key = "'emp_'+#emp.id")
    public EmpEntity editsave(EmpEntity emp){
        return emp;
    }
}
