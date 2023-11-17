package com.example.regiondemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelData {

    @ExcelProperty("县区名称")
    private String county;
    @ExcelProperty("乡镇名称")
    private String township;
    @ExcelProperty("村居名称")
    private String village;

    @ExcelProperty("手机号")
    private String phoneNum;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("职务")
    private String title;
    @ExcelProperty("入职时间")
    private String joinDate;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "county='" + county + '\'' +
                ", township='" + township + '\'' +
                ", village='" + village + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", title='" + title + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}';
    }
}
