package com.autohome.frostmourne.monitor.dao.iotdb;

import com.autohome.frostmourne.monitor.dao.iotdb.domain.IotDbRestResponse;

public interface IIotDbDao {

    IotDbRestResponse query(String user, String password, String iotDbAddr, String sql);
}
