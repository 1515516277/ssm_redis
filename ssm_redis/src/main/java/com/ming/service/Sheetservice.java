package com.ming.service;

import com.ming.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Sheetservice {
    int inserts(List<SheetEntity> sheets);

    List<SheetEntity> selsheet01(SheetEntity sheetEntity);

    int editsheet(SheetEntity sheetEntity);

    int inserts_lr(List<SheetEntity> sheets);

    List<SheetEntity> selsheet01_lr(SheetEntity sheetEntity);

    int editsheet_lr(SheetEntity sheetEntity);

    int inserts_xj(List<SheetEntity> sheets);

    List<SheetEntity> selsheet01_xj(SheetEntity sheetEntity);

    int editsheet_xj(SheetEntity sheetEntity);


    /*实收资本*/
    int insertscapital(@Param("sheets") List<CapitalEntity> sheets);

    List<CapitalEntity> selsheetcapital(CapitalEntity sheetEntity);


    /*长期待费用*/
    int insertsdeferred(@Param("sheets") List<DeferredExpenses> sheets);

    List<DeferredExpenses> selsheetdeferred(DeferredExpenses sheetEntity);

    int editsheetdeferred(DeferredExpenses sheetEntity);


    /*无形资产*/
    int insertsintangible(@Param("sheets") List<IntangibleAssets> sheets);

    List<IntangibleAssets> selsheetintangible(IntangibleAssets sheetEntity);

    int editsheetintangible(IntangibleAssets sheetEntity);


    /*库存*/
    int insertsinventory(@Param("sheets") List<InventoryEntity> sheets);

    List<InventoryEntity> selsheetinventory(InventoryEntity sheetEntity);

    int editsheetinventory(InventoryEntity sheetEntity);
}
