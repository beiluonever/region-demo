package com.example.regiondemo.mapper;

import com.example.regiondemo.domain.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {
    int insert(List<Region> regions);

}
