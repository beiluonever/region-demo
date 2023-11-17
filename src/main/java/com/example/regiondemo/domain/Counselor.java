package com.example.regiondemo.domain;

public class Counselor {
    private String id;
    private String counselorCounty;
    private String counselorTown;
    private String counselorUnit;
    private String phone;
    private String name;
    private String sex;
    private String duties;
    private String entryTime;

    public Counselor() {
    }

    public Counselor(String id, String counselorCounty, String counselorTown, String counselorUnit, String phone, String name, String sex, String duties, String entryTime) {
        this.id = id;
        this.counselorCounty = counselorCounty;
        this.counselorTown = counselorTown;
        this.counselorUnit = counselorUnit;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.duties = duties;
        this.entryTime = entryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCounselorCounty() {
        return counselorCounty;
    }

    public void setCounselorCounty(String counselorCounty) {
        this.counselorCounty = counselorCounty;
    }

    public String getCounselorTown() {
        return counselorTown;
    }

    public void setCounselorTown(String counselorTown) {
        this.counselorTown = counselorTown;
    }

    public String getCounselorUnit() {
        return counselorUnit;
    }

    public void setCounselorUnit(String counselorUnit) {
        this.counselorUnit = counselorUnit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
