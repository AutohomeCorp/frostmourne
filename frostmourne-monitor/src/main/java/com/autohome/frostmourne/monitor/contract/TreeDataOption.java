package com.autohome.frostmourne.monitor.contract;

import java.util.List;

/**
 * @author WangXiaoMing
 * @version 1.0
 * @date 2020-08-08
 */
public class TreeDataOption {

    private String code;

    private String name;

    private List<TreeDataOption> children;

    public TreeDataOption() {
    }

    public TreeDataOption(String code,
                          String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeDataOption> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDataOption> children) {
        this.children = children;
    }
}
