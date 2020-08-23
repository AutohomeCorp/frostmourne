package com.autohome.frostmourne.monitor.service.core.server;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoQueryForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSimpleContract;

public interface IServerInfoService {

    void save(ServerInfoSaveForm form,
              String account);

    Optional<ServerInfoContract> getContract(Long id);

    PagerContract<ServerInfoContract> findContract(ServerInfoQueryForm form);

    Optional<ServerInfoSimpleContract> getSimpleContract(Long id);

    Map<Long, ServerInfoSimpleContract> mapSimpleContractByIds(List<Long> ids);

}
