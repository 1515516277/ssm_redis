package com.ming.entity;


//原因表
public class ChangCause {
    private String cause01;
    private String cause02;
    private String cause03;
    private String cause04;
    private String cause05;
    private String cause06;
    private String cause07;
    private String cause08;
    private String cause09;
    private String cause10;
    private String cause11;
    private String cause12;

    public void setval(String date,String val) {
        String i="";
        try {
            switch (date) {
                case "1":
                    setCause01(val);
                    break;
                case "2":
                    setCause02(val);
                    break;
                case "3":
                    setCause03(val);
                    break;
                case "4":
                    setCause04(val);
                    break;
                case "5":
                    setCause05(val);
                    break;
                case "6":
                    setCause06(val);
                    break;
                case "7":
                    setCause07(val);
                    break;
                case "8":
                    setCause08(val);
                    break;
                case "9":
                    setCause09(val);
                    break;
                case "10":
                    setCause10(val);
                    break;
                case "11":
                    setCause11(val);
                    break;
                case "12":
                    setCause12(val);
                    break;
                default:
                    throw new Exception("日期接收错误！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getval(String date) {
        String i="";
        try {
            switch (date) {
                case "1":
                    i=getCause01();
                    break;
                case "2":
                    i=getCause02();
                    break;
                case "3":
                    i= getCause03();
                    break;
                case "4":
                    i=getCause04();
                    break;
                case "5":
                    i= getCause05();
                    break;
                case "6":
                    i= getCause06();
                    break;
                case "7":
                    i= getCause07();
                    break;
                case "8":
                    i= getCause08();
                    break;
                case "9":
                    i= getCause09();
                    break;
                case "10":
                    i= getCause10();
                    break;
                case "11":
                    i= getCause11();
                    break;
                case "12":
                    i= getCause12();
                    break;
                default:
                    throw new Exception("日期接收错误！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i==null?"":i;
    }

    @Override
    public String toString() {
        return "ChangCause{" +
                "cause01='" + cause01 + '\'' +
                ", cause02='" + cause02 + '\'' +
                ", cause03='" + cause03 + '\'' +
                ", cause04='" + cause04 + '\'' +
                ", cause05='" + cause05 + '\'' +
                ", cause06='" + cause06 + '\'' +
                ", cause07='" + cause07 + '\'' +
                ", cause08='" + cause08 + '\'' +
                ", cause09='" + cause09 + '\'' +
                ", cause10='" + cause10 + '\'' +
                ", cause11='" + cause11 + '\'' +
                ", cause12='" + cause12 + '\'' +
                '}';
    }

    public String getCause01() {
        return cause01;
    }

    public void setCause01(String cause01) {
        this.cause01 = cause01;
    }

    public String getCause02() {
        return cause02;
    }

    public void setCause02(String cause02) {
        this.cause02 = cause02;
    }

    public String getCause03() {
        return cause03;
    }

    public void setCause03(String cause03) {
        this.cause03 = cause03;
    }

    public String getCause04() {
        return cause04;
    }

    public void setCause04(String cause04) {
        this.cause04 = cause04;
    }

    public String getCause05() {
        return cause05;
    }

    public void setCause05(String cause05) {
        this.cause05 = cause05;
    }

    public String getCause06() {
        return cause06;
    }

    public void setCause06(String cause06) {
        this.cause06 = cause06;
    }

    public String getCause07() {
        return cause07;
    }

    public void setCause07(String cause07) {
        this.cause07 = cause07;
    }

    public String getCause08() {
        return cause08;
    }

    public void setCause08(String cause08) {
        this.cause08 = cause08;
    }

    public String getCause09() {
        return cause09;
    }

    public void setCause09(String cause09) {
        this.cause09 = cause09;
    }

    public String getCause10() {
        return cause10;
    }

    public void setCause10(String cause10) {
        this.cause10 = cause10;
    }

    public String getCause11() {
        return cause11;
    }

    public void setCause11(String cause11) {
        this.cause11 = cause11;
    }

    public String getCause12() {
        return cause12;
    }

    public void setCause12(String cause12) {
        this.cause12 = cause12;
    }
}
