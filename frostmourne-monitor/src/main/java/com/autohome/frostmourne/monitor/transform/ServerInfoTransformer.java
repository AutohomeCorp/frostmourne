package com.autohome.frostmourne.monitor.transform;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.autohome.frostmourne.monitor.contract.ServerInfoContract;
import com.autohome.frostmourne.monitor.contract.ServerInfoSaveForm;
import com.autohome.frostmourne.monitor.contract.ServerInfoSimpleContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServerInfo;
import org.springframework.util.CollectionUtils;

public class ServerInfoTransformer {

    public static ServerInfoContract model2Contract(ServerInfo model) {
        if (model == null) {
            return null;
        }
        ServerInfoContract data = new ServerInfoContract();
        data.setId(model.getId());
        data.setServerName(model.getServer_name());
        data.setRemark(model.getRemark());
        data.setCreator(model.getCreator());
        data.setCreateAt(model.getCreate_at());
        data.setModifier(model.getModifier());
        data.setModifyAt(model.getModify_at());
        return data;
    }

    public static List<ServerInfoContract> model2Contract(List<ServerInfo> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Collections.emptyList();
        }
        return models.stream()
                .map(ServerInfoTransformer::model2Contract)
                .collect(Collectors.toList());
    }

    public static ServerInfoSimpleContract model2SimpleContract(ServerInfo model) {
        if (model == null) {
            return null;
        }
        ServerInfoSimpleContract data = new ServerInfoSimpleContract();
        data.setId(model.getId());
        data.setServerName(model.getServer_name());
        return data;
    }

    public static ServerInfo saveForm2Model(ServerInfoSaveForm form) {
        if (form == null) {
            return null;
        }
        ServerInfo data = new ServerInfo();
        data.setId(form.getId());
        data.setServer_name(form.getServerName());
        data.setRemark(form.getRemark());
        return data;
    }

}
