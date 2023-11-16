package com.example.regiondemo.pre;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.StringUtils;
import com.example.regiondemo.domain.ExcelData;
import com.example.regiondemo.domain.Region;
import com.example.regiondemo.mapper.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
        Map<String, String> nameCodeMap = new HashMap<>();
        Map<String, Region> regionIdRegion = new HashMap<>();
        AtomicInteger i = new AtomicInteger(1);
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData demoData : dataList) {
                setRegions("1", nameCodeMap, regionIdRegion, i, demoData);
            }
        })).sheet("劝导站统计").doRead();
        EasyExcel.read(fileName, ExcelData.class, new PageReadListener<ExcelData>(dataList -> {
            for (ExcelData demoData : dataList) {
                setRegions("0", nameCodeMap, regionIdRegion, i, demoData);
            }
        })).sheet("村居统计").doRead();

        StringBuffer sql = new StringBuffer();
        regionIdRegion.forEach((id,region)->{
            String insertSql = String.format("INSERT INTO sys_region (region_id, region_name, region_type, region_parent_id, region_level)" +
                    " VALUES ('%s','%s', '%s', '%s', %d);", region.getRegionId(), region.getRegionName(), region.getRegionType(), region.getRegionParentId(), region.getRegionLevel());
            sql.append(insertSql).append("\n");
        });
        log.info("sql: {}", sql);

    }

    private static void setRegions(String regionType, Map<String, String> nameCodeMap, Map<String, Region> regionIdRegion, AtomicInteger i, ExcelData demoData) {
        String country = demoData.getCounty();
        String town = demoData.getTownship();
        String village = demoData.getVillage();

        String countryCode = nameCodeMap.getOrDefault(country, ""+ i.getAndIncrement());

        String townCode = nameCodeMap.getOrDefault(town, "" + i.getAndIncrement());
        String villageCode = nameCodeMap.getOrDefault(village, "" + i.getAndIncrement());
        nameCodeMap.putIfAbsent(country, countryCode);
        nameCodeMap.putIfAbsent(town, townCode);
        nameCodeMap.putIfAbsent(village, villageCode);

        Region villageRegion = new Region(villageCode, village, regionType, townCode, 2);
        regionIdRegion.putIfAbsent(villageCode, villageRegion);
        regionIdRegion.putIfAbsent(townCode, new Region(townCode, town, "0", countryCode, 1));
        regionIdRegion.putIfAbsent(country, new Region(countryCode, country, "0", "0", 0));
    }

}
