package com.autohome.frostmourne.monitor.service.core.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoQueryForm;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSimpleContract;

public interface IServiceInfoService {

    void save(ServiceInfoSaveForm form,
              String account);

    Optional<ServiceInfoContract> getContract(Long id);

    PagerContract<ServiceInfoContract> findContract(ServiceInfoQueryForm form);

    Optional<ServiceInfoSimpleContract> getSimpleContract(Long id);

    Map<Long, ServiceInfoSimpleContract> mapSimpleContractByIds(List<Long> ids);

}
