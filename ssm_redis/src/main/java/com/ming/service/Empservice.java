package com.ming.service;

import com.ming.entity.EmpEntity;

import java.util.List;

public interface Empservice {
     List<EmpEntity> selectall();
     int insertemp(EmpEntity emp);
     int delemp(int id);
     EmpEntity selemp(EmpEntity id);
     EmpEntity editemp(EmpEntity id);
}
