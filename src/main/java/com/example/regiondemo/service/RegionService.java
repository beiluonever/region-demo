package com.example.regiondemo.service;

import com.example.regiondemo.domain.Region;
import com.example.regiondemo.domain.RegionTreeVO;
import com.example.regiondemo.mapper.RegionMapper;
import com.mysql.cj.util.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegionService {

    @Autowired
    RegionMapper regionMapper;

    public List<RegionTreeVO> getRegionTree(String pCode, String type) {
        if(StringUtils.isNullOrEmpty(type)){
            //默认选区县
            type = "0";
        }
        boolean hasHefei = "2".equals(type);

        List<Region> regionList = regionMapper.getRegion(pCode, null);
        List<RegionTreeVO> result = new ArrayList<>();
        List<RegionTreeVO> townResult = new ArrayList<>();
        List<RegionTreeVO> vaillgeResult = new ArrayList<>();
        if (CollectionUtils.isEmpty(regionList)) {
            return result;
        }

        Map<String, List<Region>> pCodeMap = regionList.stream().collect(Collectors.groupingBy(Region::getRegionParentId));
        Map<String, List<RegionTreeVO>> townPCodeMap = new HashMap<>();
        List<RegionTreeVO> topLevel = new ArrayList<>();
        String finalType = type;
        String finalType1 = type;
        regionList.forEach(region -> {
            String code = region.getRegionId();
            RegionTreeVO self = new RegionTreeVO(region.getRegionName(), region.getRegionId(),
                    hasHefei ? region.getRegionLevel() +1 : region.getRegionLevel(), region.getRegionParentId(), region.getRegionType());
            if (region.getRegionLevel() == 1) {
                List<Region> children = pCodeMap.get(code);

                if (!CollectionUtils.isEmpty(children)) {
                    List<RegionTreeVO> treeChild = new ArrayList<>();
                    children.forEach(c ->{
                                if (c.getRegionType().equals(finalType)  || hasHefei ) {
                                    treeChild.add(new RegionTreeVO(c.getRegionName(), c.getRegionId(),
                                            hasHefei ? c.getRegionLevel() +1 : c.getRegionLevel() , c.getRegionParentId(), c.getRegionType()));
                                }
                            }
                    );
                    self.setChildren(treeChild);
                }
                townResult.add(self);
                List<RegionTreeVO> townList = townPCodeMap.getOrDefault(region.getRegionParentId(), new ArrayList<>());
                townList.add(self);
                townPCodeMap.put(region.getRegionParentId(), townList);
            } else if (region.getRegionLevel() == 0) {
                topLevel.add(self);
            } else if (region.getRegionLevel() == 2) {
                if (self.getType().equals(finalType1) || hasHefei) {
                    vaillgeResult.add(self);
                }
            }
        });
        topLevel.forEach(self -> {
            self.setChildren(townPCodeMap.get(self.getCode()));
            result.add(self);
        });
        if (!CollectionUtils.isEmpty(result)) {
            if(hasHefei){
                RegionTreeVO regionTop = new RegionTreeVO("合肥市", "0",
                        0, "-1", "0");
                regionTop.setChildren(result);
                return Collections.singletonList(regionTop);
            }
            return result;
        }
        if (!CollectionUtils.isEmpty(townResult)) {
            return townResult;
        }
        return vaillgeResult;
    }
}
