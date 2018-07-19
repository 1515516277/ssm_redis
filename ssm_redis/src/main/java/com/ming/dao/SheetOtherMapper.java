package com.ming.dao;

import com.ming.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SheetOtherMapper {
    int insertscapital(@Param("sheets") List<CapitalEntity> sheets);

    List<CapitalEntity> selsheetcapital(CapitalEntity sheetEntity);



    int insertsdeferred(@Param("sheets") List<DeferredExpenses> sheets);

    List<DeferredExpenses> selsheetdeferred(DeferredExpenses sheetEntity);

    int editsheetdeferred(DeferredExpenses sheetEntity);



    int insertsintangible(@Param("sheets") List<IntangibleAssets> sheets);

    List<IntangibleAssets> selsheetintangible(IntangibleAssets sheetEntity);

    int editsheetintangible(IntangibleAssets sheetEntity);



    int insertsinventory(@Param("sheets") List<InventoryEntity> sheets);

    List<InventoryEntity> selsheetinventory(InventoryEntity sheetEntity);

    int editsheetinventory(InventoryEntity sheetEntity);

}
