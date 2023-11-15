CREATE TABLE sys_region (
                            region_id varchar(10) NOT NULL COMMENT '地区主键编号',
                            region_name varchar(50) NOT NULL COMMENT '地区名称',
                            region_type varchar(10) DEFAULT NULL COMMENT '地区类型 0 行政区划，1 站点',
                            region_parent_id varchar(10) DEFAULT NULL COMMENT '地区父id',
                            region_level int(2) DEFAULT NULL COMMENT '地区级别 0-市辖区、县级市、县、1-乡镇、2-村落/站点',
                            PRIMARY KEY (region_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

