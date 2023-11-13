package com.example.regiondemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelData {

    @ExcelProperty("县")
    private String county;
    @ExcelProperty("乡镇")
    private String township;
    @ExcelProperty("村")
    private String village;

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

    @Override
    public String toString() {
        return "ExcelData{" +
                "county='" + county + '\'' +
                ", township='" + township + '\'' +
                ", village='" + village + '\'' +
                '}';
    }
}
