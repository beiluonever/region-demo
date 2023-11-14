package com.example.regiondemo.controller;

import com.example.regiondemo.domain.RegionTreeVO;
import com.example.regiondemo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("region")
    public List<RegionTreeVO> getRegionTree(String pCode, String type){
        return regionService.getRegionTree(pCode, type);
    }
}
