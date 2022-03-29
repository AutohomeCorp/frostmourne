package com.autohome.frostmourne.monitor.model.account;

import lombok.Getter;
import lombok.Setter;

/**
 * 团队信息
 *
 * @author Aping
 * @since 2022/3/28 13:22
 */
@Setter
@Getter
public class Team {

    private Long id;

    private String name;

    private String fullName;

    private String department;

}
