package com.ming.service.impl;

import com.ming.dao.SheetEntityLrMapper;
import com.ming.dao.SheetEntityXjMapper;
import com.ming.dao.SheetEntityZcMapper;
import com.ming.dao.SheetOtherMapper;
import com.ming.entity.*;
import com.ming.service.Sheetservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Sheetserviceimpl implements Sheetservice {
    @Autowired
    SheetEntityZcMapper sheetEntityMapper;
    @Autowired
    SheetEntityLrMapper sheetlrEntityMapper;
    @Autowired
    SheetEntityXjMapper sheetxjEntityMapper;
    @Autowired
    SheetOtherMapper sheetOtherMapper;
    @Override
    public int inserts(List<SheetEntity> sheets) {
        return sheetEntityMapper.inserts(sheets);
    }

    @Override
    public List<SheetEntity> selsheet01(SheetEntity sheetEntity) {
        return sheetEntityMapper.selsheet01(sheetEntity);
    }

    @Override
    public int editsheet(SheetEntity sheetEntity) {
        return sheetEntityMapper.editsheet(sheetEntity);
    }

    @Override
    public int inserts_lr(List<SheetEntity> sheets) {
        return sheetlrEntityMapper.inserts(sheets);
    }

    @Override
    public List<SheetEntity> selsheet01_lr(SheetEntity sheetEntity) {
        return sheetlrEntityMapper.selsheet01(sheetEntity);
    }

    @Override
    public int editsheet_lr(SheetEntity sheetEntity) {
        return sheetlrEntityMapper.editsheet(sheetEntity);
    }

    @Override
    public int inserts_xj(List<SheetEntity> sheets) {
        return sheetxjEntityMapper.inserts(sheets);
    }

    @Override
    public List<SheetEntity> selsheet01_xj(SheetEntity sheetEntity) {
        return sheetxjEntityMapper.selsheet01(sheetEntity);
    }

    @Override
    public int editsheet_xj(SheetEntity sheetEntity) {
        return sheetxjEntityMapper.editsheet(sheetEntity);
    }

    @Override
    public int insertscapital(List<CapitalEntity> sheets) {

        return sheetOtherMapper.insertscapital(sheets);
    }

    @Override
    public List<CapitalEntity> selsheetcapital(CapitalEntity sheetEntity) {
        return sheetOtherMapper.selsheetcapital(sheetEntity);
    }

    @Override
    public int insertsdeferred(List<DeferredExpenses> sheets) {
        return sheetOtherMapper.insertsdeferred(sheets);
    }

    @Override
    public List<DeferredExpenses> selsheetdeferred(DeferredExpenses sheetEntity) {
        return sheetOtherMapper.selsheetdeferred(sheetEntity);
    }

    @Override
    public int editsheetdeferred(DeferredExpenses sheetEntity) {
        return sheetOtherMapper.editsheetdeferred(sheetEntity);
    }

    @Override
    public int insertsintangible(List<IntangibleAssets> sheets) {
        return sheetOtherMapper.insertsintangible(sheets);
    }

    @Override
    public List<IntangibleAssets> selsheetintangible(IntangibleAssets sheetEntity) {
        return sheetOtherMapper.selsheetintangible(sheetEntity);
    }

    @Override
    public int editsheetintangible(IntangibleAssets sheetEntity) {
        return sheetOtherMapper.editsheetintangible(sheetEntity);
    }

    @Override
    public int insertsinventory(List<InventoryEntity> sheets) {
        return sheetOtherMapper.insertsinventory(sheets);
    }

    @Override
    public List<InventoryEntity> selsheetinventory(InventoryEntity sheetEntity) {
        return sheetOtherMapper.selsheetinventory(sheetEntity);
    }

    @Override
    public int editsheetinventory(InventoryEntity sheetEntity) {
        return sheetOtherMapper.editsheetinventory(sheetEntity);
    }
}
