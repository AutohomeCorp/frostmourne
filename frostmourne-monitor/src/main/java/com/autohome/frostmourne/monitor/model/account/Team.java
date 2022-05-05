package com.autohome.frostmourne.monitor.model.account;

/**
 * team info
 *
 * @author Aping
 * @since 2022/3/28 13:22
 */
public class Team {

    private Long id;

    private String name;

    private String fullName;

    private String department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
