package com.ming.entity;

import java.util.Date;

//实收资本表
public class CapitalEntity {
    private int id;
    private String investor;            //投资方
    private Date putDate;               //投资日期
    private String voucherNumber;       //凭证号
    private String investmentProportion;//投资比例
    private String val;                     //金额
    private String unit;                    //单位
    private String company;                 //公司
    private int companyid;                  //公司id
    private int month;  //月份
    private int years;  //年份

    @Override
    public String toString() {
        return "CapitalEntity{" +
                "id=" + id +
                ", investor='" + investor + '\'' +
                ", putDate=" + putDate +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", investmentProportion='" + investmentProportion + '\'' +
                ", val='" + val + '\'' +
                ", unit='" + unit + '\'' +
                ", company='" + company + '\'' +
                ", companyid=" + companyid +
                ", month=" + month +
                ", years=" + years +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getInvestmentProportion() {
        return investmentProportion;
    }

    public void setInvestmentProportion(String investmentProportion) {
        this.investmentProportion = investmentProportion;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }
}
