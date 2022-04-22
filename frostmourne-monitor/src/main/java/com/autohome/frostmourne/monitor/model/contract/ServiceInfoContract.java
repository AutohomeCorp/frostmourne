package com.autohome.frostmourne.monitor.model.contract;

import java.util.Date;

public class ServiceInfoContract {

    /**
     * ID
     */
    private Long id;
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 负责人
     */
    private String owner;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 最后修改人
     */
    private String modifier;
    /**
     * 最后修改时间
     */
    private Date modifyAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
        this.modifier = modifier;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}
