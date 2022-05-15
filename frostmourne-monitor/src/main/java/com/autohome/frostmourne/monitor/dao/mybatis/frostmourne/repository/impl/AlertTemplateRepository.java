package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;
import static org.mybatis.dynamic.sql.SqlBuilder.isNotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertTemplate;
import com.autohome.frostmourne.monitor.model.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertTemplateDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertTemplateDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertTemplateRepository;
import com.autohome.frostmourne.monitor.model.enums.TemplateType;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Repository
public class AlertTemplateRepository implements IAlertTemplateRepository {

    private static final Splitter SPLITTER_TEMPLATE_TYPE_UNION_CODE = Splitter.on('|').trimResults().omitEmptyStrings();
    private static final Splitter SPLITTER_TEMPLATE_UNION_CODE = Splitter.on(',').trimResults().omitEmptyStrings();

    @Resource
    private AlertTemplateDynamicMapper alertTemplateDynamicMapper;

    @Override
    public void insertSelective(AlertTemplate record) {
        alertTemplateDynamicMapper.insertSelective(record);
    }

    @Override
    public void updateByPrimaryKeySelective(AlertTemplate record) {
        alertTemplateDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Optional<AlertTemplate> getById(Long id) {
        return alertTemplateDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<AlertTemplate> find(AlertTemplateQueryForm form) {
        List<SqlCriterion<?>> templateTypeConditions;
        if (CollectionUtils.isEmpty(form.getTemplateTypeUnionCodes())) {
            templateTypeConditions = Collections.emptyList();
        } else {
            templateTypeConditions = form.getTemplateTypeUnionCodes().stream().filter(value -> !StringUtils.isEmpty(value))
                .map(SPLITTER_TEMPLATE_TYPE_UNION_CODE::splitToList)
                .map(list -> SqlBuilder.or(AlertTemplateDynamicSqlSupport.templateType, isEqualTo(TemplateType.valueOf(list.get(0))).when(MybatisTool::notNull),
                    and(AlertTemplateDynamicSqlSupport.templateUnionCode,
                        isIn(list.size() > 1 ? SPLITTER_TEMPLATE_UNION_CODE.splitToList(list.get(1)) : Collections.emptyList())
                            .then(s -> s.filter(MybatisTool::notNullAndEmpty)))))
                .collect(Collectors.toList());
        }
        PageHelper.startPage(form.getPageIndex(), form.getPageSize());
        List<AlertTemplate> records = alertTemplateDynamicMapper.select(query -> {
            query.where()
                .and(AlertTemplateDynamicSqlSupport.templateName,
                    isLike(form.getTemplateName()).when(MybatisTool::notNullAndEmpty).then(MybatisTool::twoSideVagueMatch))
                .and(AlertTemplateDynamicSqlSupport.templateType, isEqualTo(form.getTemplateType()).when(MybatisTool::notNull))
                .and(AlertTemplateDynamicSqlSupport.templateType, isNotNull().when(() -> false), templateTypeConditions)
                .orderBy(AlertTemplateDynamicSqlSupport.createAt.descending());
            return query;
        });
        return new PageInfo<>(records);
    }

}
