package com.autohome.frostmourne.monitor.service.core.server;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoQueryForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSimpleContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServerInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IServerInfoRepository;
import com.autohome.frostmourne.monitor.transform.ServerInfoTransformer;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ServerInfoService implements IServerInfoService {

    @Resource
    private IServerInfoRepository serverInfoRepository;

    @Override
    public void save(ServerInfoSaveForm form,
                     String account) {
        ServerInfo record = ServerInfoTransformer.saveForm2Model(form);
        record.setModifier(account);
        if (record.getId() == null || record.getId() == 0L) {
            // 新增
            record.setCreator(account);
            serverInfoRepository.insertSelective(record);
        } else {
            serverInfoRepository.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public Optional<ServerInfoContract> getContract(Long id) {
        return serverInfoRepository.getById(id)
                .map(ServerInfoTransformer::model2Contract);
    }

    @Override
    public PagerContract<ServerInfoContract> findContract(ServerInfoQueryForm form) {
        PageInfo<ServerInfo> recordPage = serverInfoRepository.find(form);
        List<ServerInfoContract> contracts = ServerInfoTransformer.model2Contract(recordPage.getList());
        return new PagerContract<>(contracts, recordPage.getPageSize(), recordPage.getPageNum(), (int) recordPage.getTotal());
    }

    @Override
    public Optional<ServerInfoSimpleContract> getSimpleContract(Long id) {
        return serverInfoRepository.getById(id)
                .map(ServerInfoTransformer::model2SimpleContract);
    }

    @Override
    public Map<Long, ServerInfoSimpleContract> mapSimpleContractByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<ServerInfo> list = serverInfoRepository.listByIds(ids);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream()
                .collect(Collectors.toMap(ServerInfo::getId, ServerInfoTransformer::model2SimpleContract, (v1, v2) -> v1));
    }

}
