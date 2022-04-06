package com.autohome.frostmourne.monitor.model.account;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户帐号信息
 *
 * @author Aping
 * @since 2022/3/28 13:22
 */
@Getter
@Setter
public class AccountInfo {

    private String account;

    private String fullName;

    private Long teamId;

    private String teamName;

    private String mobile;

    private String email;

    private String wxid;

    private Long departmentId;

    private List<String> roles;
}
