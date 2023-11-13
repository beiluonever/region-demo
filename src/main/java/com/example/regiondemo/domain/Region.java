package com.example.regiondemo.domain;

public class Region {
    private String regionId;
    private String regionName;
    private String regionType;
    private String regionParentId;
    private Integer regionLevel;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getRegionParentId() {
        return regionParentId;
    }

    public void setRegionParentId(String regionParentId) {
        this.regionParentId = regionParentId;
    }

    public Integer getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionId='" + regionId + '\'' +
                ", regionName='" + regionName + '\'' +
                ", regionType='" + regionType + '\'' +
                ", regionParentId='" + regionParentId + '\'' +
                ", regionLevel=" + regionLevel +
                '}';
    }
}
