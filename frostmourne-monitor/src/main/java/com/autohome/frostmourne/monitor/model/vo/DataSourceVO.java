package com.autohome.frostmourne.monitor.model.vo;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import lombok.Builder;
import lombok.Data;

/**
 * description
 *
 * @author fangpeng
 * @since 2022/9/14 18:55
 */
@Data
@Builder
public class DataSourceVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 数据源名称
     */
    private String datasourceName;

    /**
     * 数据源类型。(Elasticsearch, Influxdb)
     */
    private DataSourceType datasourceType;

}
