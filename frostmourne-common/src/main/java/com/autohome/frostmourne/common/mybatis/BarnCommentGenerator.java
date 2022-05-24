package com.autohome.frostmourne.common.mybatis;

import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

public class BarnCommentGenerator extends DefaultCommentGenerator {

    private static final String AUTHOR = "mybatis-generator";

    protected void appendRemarks(String title, String remarks, Consumer<String> remarkLineConsumer) {
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split("\r?\n");
            remarkLineConsumer.accept(" * " + Optional.ofNullable(title).orElse("") + remarkLines[0]);
            for (int i = 1; i < remarkLines.length; i++) {
                remarkLineConsumer.accept(" * " + remarkLines[i]);
            }
        }
    }

    protected String getTableRemarks(IntrospectedTable introspectedTable) {
        return StringUtility.stringHasValue(introspectedTable.getRemarks()) ? introspectedTable.getRemarks()
            : introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
    }

    protected String getColumnRemarks(IntrospectedColumn introspectedColumn) {
        return StringUtility.stringHasValue(introspectedColumn.getRemarks()) ? introspectedColumn.getRemarks() : introspectedColumn.getActualColumnName();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 库表字段属性注解，targetRuntime=MyBatis3
        field.addJavaDocLine("/**");
        this.appendRemarks(null, this.getColumnRemarks(introspectedColumn), field::addJavaDocLine);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        // 非库表字段属性注解，targetRuntime=MyBatis3
        // 如：Model.serialVersionUID, Example.orderByClause
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // Model类注解
        topLevelClass.addJavaDocLine("/**");
        this.appendRemarks(null, this.getTableRemarks(introspectedTable), topLevelClass::addJavaDocLine);
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + AUTHOR);
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        // 内部类注解，targetRuntime=MyBatis3
        // 如：Example.GeneratedCriteria
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        // 内部类注解，targetRuntime=MyBatis3
        // 如：Example.Criteria
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        // 内部枚举类注解，暂无
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 库表字段get方法注解，targetRuntime=MyBatis3
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 库表字段set方法注解，targetRuntime=MyBatis3
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 非库表方法注解，targetRuntime=MyBatis3
        // 如：Example.setOrderByClause(), Mapper.insert()
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // Java类文件注解，加在第一行
        // 如：Model, Example, Mapper
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        // xml节点注解，targetRuntime=MyBatis3
        // 如：<resultMap>,<sql>
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        // xml根节点注解，targetRuntime=MyBatis3
        // 如：<mapper>
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        // 非库表字段方法注解，targetRuntime=MyBatis3DynamicSQL
        // 如：Mapper.count()
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
        Set<FullyQualifiedJavaType> imports) {
        // 库表字段方法注解，targetRuntime=MyBatis3DynamicSQL
        // 如：Model.getXxx(), Model.setXxx()
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        // 非库表字段属性注解，targetRuntime=MyBatis3DynamicSQL
        // 如：Model.serialVersionUID, Mapper.selectList, DynamicSqlSupport.table
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
        Set<FullyQualifiedJavaType> imports) {
        // 库表字段属性注解，targetRuntime=MyBatis3DynamicSQL
        field.addJavaDocLine("/**");
        this.appendRemarks(null, this.getColumnRemarks(introspectedColumn), field::addJavaDocLine);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        // 内部类注解，targetRuntime=MyBatis3DynamicSQL
        // 如：DynamicSqlSupport.SqlTable
    }

}
