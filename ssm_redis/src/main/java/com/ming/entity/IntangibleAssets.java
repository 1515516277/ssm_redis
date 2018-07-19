package com.ming.entity;

import java.util.Date;

//无形资产
public class IntangibleAssets {
    private int id;
    private String proCode;     //资产编码
    private String proName;     //资产编码
    private String measureUnit; //计量单位
    private String proNum;      //资产数量
    private Date dateRecorded;  //入账日期
    private String proOriVal;       //资产原值
    private int makeNum;           //使用期数
    private int undoneExpect;       //已撤销期数
    private String expectRepeal;    //每期撤销金额
    private String sumRepealVal;    //累计撤销金额
    private String worth;              //净值
    private String dept;        //使用部门
    private String place;       //存放地点
    private String sumVal;      //合同总金额
    private String remake;      //备注
    private String company;     //公司
    private int companyid;
    private String unit;
    private int month;  //月份
    private int years;  //年份

    @Override
    public String toString() {
        return "intangibleAssets{" +
                "id=" + id +
                ", proCode='" + proCode + '\'' +
                ", proName='" + proName + '\'' +
                ", measureUnit='" + measureUnit + '\'' +
                ", proNum='" + proNum + '\'' +
                ", dateRecorded=" + dateRecorded +
                ", proOriVal='" + proOriVal + '\'' +
                ", makeNum=" + makeNum +
                ", undoneExpect=" + undoneExpect +
                ", expectRepeal='" + expectRepeal + '\'' +
                ", sumRepealVal='" + sumRepealVal + '\'' +
                ", worth='" + worth + '\'' +
                ", dept='" + dept + '\'' +
                ", place='" + place + '\'' +
                ", sumVal='" + sumVal + '\'' +
                ", remake='" + remake + '\'' +
                ", company='" + company + '\'' +
                ", companyid=" + companyid +
                ", unit='" + unit + '\'' +
                ", month=" + month +
                ", years=" + years +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getProOriVal() {
        return proOriVal;
    }

    public void setProOriVal(String proOriVal) {
        this.proOriVal = proOriVal;
    }

    public int getMakeNum() {
        return makeNum;
    }

    public void setMakeNum(int makeNum) {
        this.makeNum = makeNum;
    }

    public int getUndoneExpect() {
        return undoneExpect;
    }

    public void setUndoneExpect(int undoneExpect) {
        this.undoneExpect = undoneExpect;
    }

    public String getExpectRepeal() {
        return expectRepeal;
    }

    public void setExpectRepeal(String expectRepeal) {
        this.expectRepeal = expectRepeal;
    }

    public String getSumRepealVal() {
        return sumRepealVal;
    }

    public void setSumRepealVal(String sumRepealVal) {
        this.sumRepealVal = sumRepealVal;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSumVal() {
        return sumVal;
    }

    public void setSumVal(String sumVal) {
        this.sumVal = sumVal;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
}
