package com.ming.entity;
//库存
public class InventoryEntity {
    private int id;
    private String type;  //存货类型
    private String name;    //名称及规格
    private int startNum;   //期初数量
    private double startVal;    //期初金额
    private int buyNum;     //购进数量
    private double buyVal;      //购进金额
    private int stockNum;       //出库数量
    private double stockVal;       //出库金额
    private int balanceNum;     //结存数量
    private double balanceVal;     //结存金额
    private String remark;      //备注
    private String company;     //公司
    private  int companyid;     //公司ｉｄ
    private String unit;        //单位
    private int month;  //月份
    private int years;  //年份

    @Override
    public String toString() {
        return "InventoryEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", startNum=" + startNum +
                ", startVal=" + startVal +
                ", buyNum=" + buyNum +
                ", buyVal=" + buyVal +
                ", stockNum=" + stockNum +
                ", stockVal=" + stockVal +
                ", balanceNum=" + balanceNum +
                ", balanceVal=" + balanceVal +
                ", remark='" + remark + '\'' +
                ", company='" + company + '\'' +
                ", companyid=" + companyid +
                ", unit='" + unit + '\'' +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public double getStartVal() {
        return startVal;
    }

    public void setStartVal(double startVal) {
        this.startVal = startVal;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getBuyVal() {
        return buyVal;
    }

    public void setBuyVal(double buyVal) {
        this.buyVal = buyVal;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public double getStockVal() {
        return stockVal;
    }

    public void setStockVal(double stockVal) {
        this.stockVal = stockVal;
    }

    public int getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(int balanceNum) {
        this.balanceNum = balanceNum;
    }

    public double getBalanceVal() {
        return balanceVal;
    }

    public void setBalanceVal(double balanceVal) {
        this.balanceVal = balanceVal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
