package com.example.regiondemo.service;

import com.example.regiondemo.domain.Region;
import com.example.regiondemo.domain.RegionTreeVO;
import com.example.regiondemo.mapper.RegionMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionMapper regionMapper;

    public List<RegionTreeVO> getRegionTree(String pCode, String type) {
        if(StringUtils.isNullOrEmpty(type)){
            type = "2";
        }
        List<Region> regionList = regionMapper.getRegion(pCode, null);
        List<RegionTreeVO> result = new ArrayList<>();
        String finalType = type;
        regionList.forEach(region->{
            if("0".equals(region.getRegionType()) || region.getRegionType().equals(finalType)) {
                result.add(new RegionTreeVO(region.getRegionName(), region.getRegionId(),
                        region.getRegionLevel(), region.getRegionParentId(), region.getRegionType()));
            }
        });
        return buildTree(result);
    }



    public List<RegionTreeVO> buildTree(List<RegionTreeVO> regions) {
        List<RegionTreeVO> returnList = new ArrayList<>();
        List<String> tempList = regions.stream().map(RegionTreeVO::getCode).toList();
        for (RegionTreeVO region : regions) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(region.getpCode())) {
                recursionFn(regions, region);
                returnList.add(region);
            }
        }
        if (returnList.isEmpty()) {
            returnList = regions;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<RegionTreeVO> list, RegionTreeVO t) {
        // 得到子节点列表
        List<RegionTreeVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (RegionTreeVO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 得到子节点列表
     */
    private List<RegionTreeVO> getChildList(List<RegionTreeVO> list, RegionTreeVO t) {
        List<RegionTreeVO> tlist = new ArrayList<>();
        for (RegionTreeVO n : list) {
            if (!StringUtils.isNullOrEmpty(n.getpCode()) && n.getpCode().equals(t.getCode())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<RegionTreeVO> list, RegionTreeVO t) {
        return getChildList(list, t).size() > 0;
    }

}
