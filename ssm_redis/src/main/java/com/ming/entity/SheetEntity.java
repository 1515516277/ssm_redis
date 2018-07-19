package com.ming.entity;


//导出表
public class SheetEntity {
    private Integer id;     //id
    private String subjectName;//项目名称
    private double beginYearBalance;//年余额
    private String corpName;//公司
    private double month01;     //一月
    private double month02;     //二月
    private double month03;     //  3
    private double month04;     //4
    private double month05;     // 5
    private double month06;     // 6
    private double month07;     // 7
    private double month08;     //8
    private double month09;    // 9
    private double month10;     //10
    private double month11;     //11
    private double month12;    //12
    private String years;       //年
    private String changeCause;    //原因
    private String unit;   //单位
    private double yearTotal; //'本年累计额'
    private double lastyears;//去年累计额


    public double getlastyears(String date){
        int c=Integer.parseInt(date);
        if(c<1){
            return 0.0;
        }
        date = Integer.parseInt(date)+"";
        double result=0.0;
        try {
            switch (date) {
                case "1":
                    result=getMonth01();
                    break;
                case "2":
                    result=getMonth01()+getMonth02();
                    break;
                case "3":
                    result=getMonth01()+getMonth02()+getMonth03();
                    break;
                case "4":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04();
                    break;
                case "5":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05();
                    break;
                case "6":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06();
                    break;
                case "7":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07();
                    break;
                case "8":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07()+getMonth08();
                    break;
                case "9":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07()+getMonth08()+getMonth09();
                    break;
                case "10":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07()+getMonth08()+getMonth09()+getMonth10();
                    break;
                case "11":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07()+getMonth08()+getMonth09()+getMonth10()+getMonth11();
                    break;
                case "12":
                    result=getMonth01()+getMonth02()+getMonth03()+getMonth04()+getMonth05()+getMonth06()+getMonth07()+getMonth08()+getMonth09()+getMonth10()+getMonth11()+getMonth12();
                    break;
                default:
                    throw new Exception("日期接收错误！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void ucc(String date, double val) {
        int c=Integer.parseInt(date);
        if(c<1){
            return;
        }
        date = Integer.parseInt(date)+"";
        try {
            switch (date) {
                case "1":
                    setMonth01(val);
                    break;
                case "2":
                    setMonth02(val);
                    break;
                case "3":
                    setMonth03(val);
                    break;
                case "4":
                    setMonth04(val);
                    break;
                case "5":
                    setMonth05(val);
                    break;
                case "6":
                    setMonth06(val);
                    break;
                case "7":
                    setMonth07(val);
                    break;
                case "8":
                    setMonth08(val);
                    break;
                case "9":
                    setMonth09(val);
                    break;
                case "10":
                    setMonth10(val);
                    break;
                case "11":
                    setMonth11(val);
                    break;
                case "12":
                    setMonth12(val);
                    break;
                default:
                    throw new Exception("日期接收错误！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public double getval(String date) {

        double i=0.0;
        try {
            int c=Integer.parseInt(date);
            if(c<1){
                return 0.0;
            }
            date=c+"";
            switch (date) {
                case "1":
                    i=getMonth01();
                    break;
                case "2":
                    i=getMonth02();
                    break;
                case "3":
                    i= getMonth03();
                    break;
                case "4":
                    i=getMonth04();
                    break;
                case "5":
                    i= getMonth05();
                    break;
                case "6":
                    i= getMonth06();
                    break;
                case "7":
                    i= getMonth07();
                    break;
                case "8":
                    i= getMonth08();
                    break;
                case "9":
                    i= getMonth09();
                    break;
                case "10":
                    i= getMonth10();
                    break;
                case "11":
                    i= getMonth11();
                    break;
                case "12":
                    i= getMonth12();
                    break;
                default:
                    throw new Exception("日期接收错误！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }


    @Override
    public String toString() {
        return "SheetEntity{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", beginYearBalance=" + beginYearBalance +
                ", corpName='" + corpName + '\'' +
                ", month01=" + month01 +
                ", month02=" + month02 +
                ", month03=" + month03 +
                ", month04=" + month04 +
                ", month05=" + month05 +
                ", month06=" + month06 +
                ", month07=" + month07 +
                ", month08=" + month08 +
                ", month09=" + month09 +
                ", month10=" + month10 +
                ", month11=" + month11 +
                ", month12=" + month12 +
                ", years='" + years + '\'' +
                ", changeCause='" + changeCause + '\'' +
                ", unit='" + unit + '\'' +
                ", yearTotal=" + yearTotal +
                ", lastyears=" + lastyears +
                '}';
    }

    public double getLastyears() {
        return lastyears>0?lastyears:0;
    }

    public void setLastyears(double lastyears) {
        this.lastyears = lastyears;
    }

    public double getYearTotal() {
        return yearTotal;
    }

    public void setYearTotal(double yearTotal) {
        this.yearTotal = yearTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getBeginYearBalance() {
        return beginYearBalance;
    }

    public void setBeginYearBalance(double beginYearBalance) {
        this.beginYearBalance = beginYearBalance;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public double getMonth01() {
        return month01;
    }

    public void setMonth01(double month01) {
        this.month01 = month01;
    }

    public double getMonth02() {
        return month02;
    }

    public void setMonth02(double month02) {
        this.month02 = month02;
    }

    public double getMonth03() {
        return month03;
    }

    public void setMonth03(double month03) {
        this.month03 = month03;
    }

    public double getMonth04() {
        return month04;
    }

    public void setMonth04(double month04) {
        this.month04 = month04;
    }

    public double getMonth05() {
        return month05;
    }

    public void setMonth05(double month05) {
        this.month05 = month05;
    }

    public double getMonth06() {
        return month06;
    }

    public void setMonth06(double month06) {
        this.month06 = month06;
    }

    public double getMonth07() {
        return month07;
    }

    public void setMonth07(double month07) {
        this.month07 = month07;
    }

    public double getMonth08() {
        return month08;
    }

    public void setMonth08(double month08) {
        this.month08 = month08;
    }

    public double getMonth09() {
        return month09;
    }

    public void setMonth09(double month09) {
        this.month09 = month09;
    }

    public double getMonth10() {
        return month10;
    }

    public void setMonth10(double month10) {
        this.month10 = month10;
    }

    public double getMonth11() {
        return month11;
    }

    public void setMonth11(double month11) {
        this.month11 = month11;
    }

    public double getMonth12() {
        return month12;
    }

    public void setMonth12(double month12) {
        this.month12 = month12;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getChangeCause() {
        return changeCause;
    }

    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
