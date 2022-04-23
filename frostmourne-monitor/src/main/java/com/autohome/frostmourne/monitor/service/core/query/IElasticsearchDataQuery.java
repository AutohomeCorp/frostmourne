package com.autohome.frostmourne.monitor.service.core.query;

import java.io.IOException;
import java.util.List;

import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;

public interface IElasticsearchDataQuery {

    ElasticsearchDataResult query(DataNameContract dataNameContract, DataSourceContract dataSourceContract, DateTime start, DateTime end, String esQuery,
        String scrollId, String sortOrder, Integer intervalInSeconds);

    /**
     * 查询索引的字段列表
     *
     * @param dataNameContract 数据名
     * @param dataSourceContract 数据源
     * @return 字段列表
     */
    List<String> queryMappingFileds(DataNameContract dataNameContract, DataSourceContract dataSourceContract);

    MetricData queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException;
}
