package com.example.regiondemo.service;

import com.example.regiondemo.domain.Region;
import com.example.regiondemo.domain.RegionTreeVO;
import com.example.regiondemo.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegionService {

    @Autowired
    RegionMapper regionMapper;

    public List<RegionTreeVO> getRegionTree(String pCode, String type) {
        List<Region> regionList = regionMapper.getRegion(pCode, type);
        List<RegionTreeVO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(regionList)){
            return result;
        }

        Map<String, List<Region>> pCodeMap = regionList.stream().collect(Collectors.groupingBy(Region::getRegionParentId));
        Map<String, List<RegionTreeVO>> townPCodeMap = new HashMap<>();
        List<RegionTreeVO> topLevel = new ArrayList<>();
        regionList.forEach(region -> {
            String code = region.getRegionId();
            RegionTreeVO self = new RegionTreeVO(region.getRegionName(), region.getRegionId(),
                    region.getRegionLevel(), region.getRegionParentId(), region.getRegionType());
            if (region.getRegionLevel() == 1) {
                List<Region> children = pCodeMap.get(code);

                if (!CollectionUtils.isEmpty(children)) {
                    List<RegionTreeVO> treeChild = new ArrayList<>();
                    children.forEach(c -> treeChild.add(new RegionTreeVO(c.getRegionName(), c.getRegionId(),
                            c.getRegionLevel(), c.getRegionParentId(), c.getRegionType())));
                    self.setChildren(treeChild);
                }

                List<RegionTreeVO> townList = townPCodeMap.getOrDefault(region.getRegionParentId(),new ArrayList<>());
                townList.add(self);
                townPCodeMap.put(region.getRegionParentId(), townList);
            }else if(region.getRegionLevel() == 0){
                topLevel.add(self);
            }
        });
        topLevel.forEach(self -> {
            self.setChildren(townPCodeMap.get(self.getCode()));
            result.add(self);
        });

        return result;
    }
}
