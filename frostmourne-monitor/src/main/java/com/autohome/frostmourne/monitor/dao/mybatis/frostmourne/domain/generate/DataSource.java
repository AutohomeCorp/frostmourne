package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据源
 *
 * @author mybatis-generator
 */
public class DataSource implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 数据源名称
     */
    private String datasourceName;

    /**
     * 数据源类型。(Elasticsearch, Influxdb)
     */
    private DataSourceType datasourceType;

    /**
     * 数据源服务地址
     */
    private String serviceAddress;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyAt;

    /**
     * 附加属性。json格式
     */
    private String properties;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName == null ? null : datasourceName.trim();
    }

    public DataSourceType getDatasourceType() {
        return datasourceType;
    }

    public void setDatasourceType(DataSourceType datasourceType) {
        this.datasourceType = datasourceType;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress == null ? null : serviceAddress.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DataSource other = (DataSource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDatasourceName() == null ? other.getDatasourceName() == null : this.getDatasourceName().equals(other.getDatasourceName()))
            && (this.getDatasourceType() == null ? other.getDatasourceType() == null : this.getDatasourceType().equals(other.getDatasourceType()))
            && (this.getServiceAddress() == null ? other.getServiceAddress() == null : this.getServiceAddress().equals(other.getServiceAddress()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyAt() == null ? other.getModifyAt() == null : this.getModifyAt().equals(other.getModifyAt()))
            && (this.getProperties() == null ? other.getProperties() == null : this.getProperties().equals(other.getProperties()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDatasourceName() == null) ? 0 : getDatasourceName().hashCode());
        result = prime * result + ((getDatasourceType() == null) ? 0 : getDatasourceType().hashCode());
        result = prime * result + ((getServiceAddress() == null) ? 0 : getServiceAddress().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyAt() == null) ? 0 : getModifyAt().hashCode());
        result = prime * result + ((getProperties() == null) ? 0 : getProperties().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "id=" + id +
                ", datasourceName='" + datasourceName + '\'' +
                ", datasourceType=" + datasourceType +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", creator='" + creator + '\'' +
                ", createAt=" + createAt +
                ", modifier='" + modifier + '\'' +
                ", modifyAt=" + modifyAt +
                ", properties='" + properties + '\'' +
                '}';
    }
}