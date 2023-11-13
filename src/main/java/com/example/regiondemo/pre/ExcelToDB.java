package com.example.regiondemo.pre;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.StringUtils;
import com.example.regiondemo.domain.ExcelData;
import com.example.regiondemo.domain.Region;
import com.example.regiondemo.mapper.RegionMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ExcelToDB {

    @Autowired
    RegionMapper regionMapper;

    public boolean processExcelRegionToDB(String fileName){
        if(StringUtils.isBlank(fileName)){
            fileName = "demo.xlsx";
        }
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData demoData : dataList) {
                log.info("读取到一条数据{}", demoData);
            }
        })).sheet().doRead();
        return true;
    };

    public static void main(String[] args) {
        String  fileName = "src/main/resources/demo.xlsx";
        Map<String,Map<String, Region>> countryTownMap = new HashMap<>();
        Map<String, String> nameCodeMap = new HashMap<>();
        Set<Region> regions = new HashSet<>();
        AtomicInteger i = new AtomicInteger(1);
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData demoData : dataList) {
                String country = demoData.getCounty();
                String town = demoData.getTownship();
                String village = demoData.getVillage();

                String countryCode = nameCodeMap.getOrDefault(country, i.getAndIncrement()+ "");
                String townCode = nameCodeMap.getOrDefault(town, i.getAndIncrement()+ "");
                String villageCode = nameCodeMap.getOrDefault(village, i.getAndIncrement()+ "");
                Region temp = new Region();
                temp.setRegionLevel(2);
                temp.setRegionId(villageCode);
                temp.setRegionName(townCode);
                temp.setRegionType("0");
                temp.setRegionParentId(townCode);


            }
        })).sheet().doRead();
    }

}
