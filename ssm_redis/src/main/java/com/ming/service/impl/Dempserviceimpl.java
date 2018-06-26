package com.ming.service.impl;

import com.ming.dao.DempEntityMapper;
import com.ming.entity.DempEntity;
import com.ming.service.Dempservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dempserviceimpl implements Dempservice {
    @Autowired
    DempEntityMapper dempEntityMapper;
    public static final String VAL="redisCacheManager";
    @Cacheable(value = VAL,key = "'demp'")
    public List<DempEntity> selDemp() {
        return dempEntityMapper.selDemp();
    }
}
