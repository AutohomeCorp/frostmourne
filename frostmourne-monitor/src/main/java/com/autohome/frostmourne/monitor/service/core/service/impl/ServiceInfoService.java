package com.autohome.frostmourne.monitor.service.core.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoQueryForm;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSimpleContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServiceInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IServiceInfoRepository;
import com.autohome.frostmourne.monitor.service.core.service.IServiceInfoService;
import com.autohome.frostmourne.monitor.transform.ServiceInfoTransformer;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ServiceInfoService implements IServiceInfoService {

    @Resource
    private IServiceInfoRepository serviceInfoRepository;

    @Override
    public void save(ServiceInfoSaveForm form,
                     String account) {
        ServiceInfo record = ServiceInfoTransformer.saveForm2Model(form);
        record.setModifier(account);
        if (record.getId() == null || record.getId() == 0L) {
            // 新增
            record.setCreator(account);
            serviceInfoRepository.insertSelective(record);
        } else {
            serviceInfoRepository.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public Optional<ServiceInfoContract> getContract(Long id) {
        return serviceInfoRepository.getById(id)
                .map(ServiceInfoTransformer::model2Contract);
    }

    @Override
    public PagerContract<ServiceInfoContract> findContract(ServiceInfoQueryForm form) {
        PageInfo<ServiceInfo> recordPage = serviceInfoRepository.find(form);
        List<ServiceInfoContract> contracts = ServiceInfoTransformer.model2Contract(recordPage.getList());
        return new PagerContract<>(contracts, recordPage.getPageSize(), recordPage.getPageNum(), (int) recordPage.getTotal());
    }

    @Override
    public Optional<ServiceInfoSimpleContract> getSimpleContract(Long id) {
        return serviceInfoRepository.getById(id)
                .map(ServiceInfoTransformer::model2SimpleContract);
    }

    @Override
    public Map<Long, ServiceInfoSimpleContract> mapSimpleContractByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<ServiceInfo> list = serviceInfoRepository.listByIds(ids);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream()
                .collect(Collectors.toMap(ServiceInfo::getId, ServiceInfoTransformer::model2SimpleContract, (v1, v2) -> v1));
    }

}
