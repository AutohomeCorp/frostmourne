package com.autohome.frostmourne.monitor.service.core.template;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateContract;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateSaveForm;
import com.autohome.frostmourne.monitor.model.contract.TreeDataOption;

public interface IAlertTemplateService {

    void save(AlertTemplateSaveForm form,
              String account);

    Optional<AlertTemplateContract> getContract(Long id);

    PagerContract<AlertTemplateContract> findContract(AlertTemplateQueryForm form);

    List<TreeDataOption> listTemplateTypeOptions();

}
