package com.autohome.frostmourne.monitor.transform;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.autohome.frostmourne.monitor.contract.ServiceInfoContract;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServiceInfoSimpleContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServiceInfo;
import org.springframework.util.CollectionUtils;

public class ServiceInfoTransformer {

    public static ServiceInfoContract model2Contract(ServiceInfo model) {
        if (model == null) {
            return null;
        }
        ServiceInfoContract data = new ServiceInfoContract();
        data.setId(model.getId());
        data.setServiceName(model.getServiceName());
        data.setRemark(model.getRemark());
        data.setOwner(model.getOwner());
        data.setCreator(model.getCreator());
        data.setCreateAt(model.getCreateAt());
        data.setModifier(model.getModifier());
        data.setModifyAt(model.getModifyAt());
        return data;
    }

    public static List<ServiceInfoContract> model2Contract(List<ServiceInfo> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Collections.emptyList();
        }
        return models.stream()
                .map(ServiceInfoTransformer::model2Contract)
                .collect(Collectors.toList());
    }

    public static ServiceInfoSimpleContract model2SimpleContract(ServiceInfo model) {
        if (model == null) {
            return null;
        }
        ServiceInfoSimpleContract data = new ServiceInfoSimpleContract();
        data.setId(model.getId());
        data.setServiceName(model.getServiceName());
        return data;
    }

    public static ServiceInfo saveForm2Model(ServiceInfoSaveForm form) {
        if (form == null) {
            return null;
        }
        ServiceInfo data = new ServiceInfo();
        data.setId(form.getId());
        data.setServiceName(form.getServiceName());
        data.setRemark(form.getRemark());
        return data;
    }

}
