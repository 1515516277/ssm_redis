package com.ming.entity;


//长期待费用
public class DeferredExpenses {
    private int id;
    private String content;     //摊销内容
    private String supplier;       //供应商
    private String period;          //摊销期间
    private String periods;         //摊销期间
    private String undonePeriods;   //已撤销期数
    private String sumVal;             //总金额
    private String repealVal;           //本期摊销额
    private String sumRepeal;           //累计摊销
    private String witeRepealVal;       //带摊销余额
    private String project;         //摊入科目
    private String remake;          //备注
    private String unit;
    private String company;
    private String companyid;
    private int month;  //月份
    private int years;  //年份

    @Override
    public String toString() {
        return "deferredExpenses{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", supplier='" + supplier + '\'' +
                ", period='" + period + '\'' +
                ", periods='" + periods + '\'' +
                ", undonePeriods='" + undonePeriods + '\'' +
                ", sumVal='" + sumVal + '\'' +
                ", repealVal='" + repealVal + '\'' +
                ", sumRepeal='" + sumRepeal + '\'' +
                ", witeRepealVal='" + witeRepealVal + '\'' +
                ", project='" + project + '\'' +
                ", remake='" + remake + '\'' +
                ", unit='" + unit + '\'' +
                ", company='" + company + '\'' +
                ", companyid='" + companyid + '\'' +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getUndonePeriods() {
        return undonePeriods;
    }

    public void setUndonePeriods(String undonePeriods) {
        this.undonePeriods = undonePeriods;
    }

    public String getSumVal() {
        return sumVal;
    }

    public void setSumVal(String sumVal) {
        this.sumVal = sumVal;
    }

    public String getRepealVal() {
        return repealVal;
    }

    public void setRepealVal(String repealVal) {
        this.repealVal = repealVal;
    }

    public String getSumRepeal() {
        return sumRepeal;
    }

    public void setSumRepeal(String sumRepeal) {
        this.sumRepeal = sumRepeal;
    }

    public String getWiteRepealVal() {
        return witeRepealVal;
    }

    public void setWiteRepealVal(String witeRepealVal) {
        this.witeRepealVal = witeRepealVal;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
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

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }
}
