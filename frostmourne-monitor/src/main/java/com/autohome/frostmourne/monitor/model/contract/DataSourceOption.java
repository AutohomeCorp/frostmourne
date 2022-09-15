package com.autohome.frostmourne.monitor.model.contract;

import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.model.vo.DataSourceVO;
import lombok.Data;

@Data
public class DataSourceOption {

    private DataSourceVO dataSourceVO;

    private List<DataNameContract> dataNameContractList;

}
