package com.ming.dao;

import com.ming.entity.SheetEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SheetEntityZcMapper {
    int inserts(@Param("sheets") List<SheetEntity> sheets);

    List<SheetEntity> selsheet01(SheetEntity sheetEntity);

    int editsheet(SheetEntity sheetEntity);
}
