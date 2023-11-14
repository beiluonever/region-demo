package com.example.regiondemo.domain;

import java.util.List;

public class RegionTreeVO {

    private String name;
    private String code;
    private Integer level;
    private String pCode;
    //0 区域，1 检查站
    private String type;
    private List<RegionTreeVO> children;

    public RegionTreeVO(String name, String code, Integer level, String pCode, String type, List<RegionTreeVO> children) {
        this.name = name;
        this.code = code;
        this.level = level;
        this.type = type;
        this.pCode = pCode;
        this.children = children;
    }

    public RegionTreeVO(String name, String code, Integer level, String pCode, String type) {
        this.name = name;
        this.code = code;
        this.level = level;
        this.type = type;
        this.pCode = pCode;
    }

    public RegionTreeVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RegionTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<RegionTreeVO> children) {
        this.children = children;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }
}
