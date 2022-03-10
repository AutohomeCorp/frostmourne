package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class ShortLink {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.198+08:00", comments="Source field: short_link.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.long_link")
    private String longLink;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.2+08:00", comments="Source field: short_link.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.long_link")
    public String getLongLink() {
        return longLink;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.long_link")
    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.201+08:00", comments="Source field: short_link.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}