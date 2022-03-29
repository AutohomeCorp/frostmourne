package com.autohome.frostmourne.monitor.model.message;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 告警消息业务上下文
 *
 * @author Aping
 * @since 2022/3/28 13:21
 */
@Getter
@Setter
public class AlarmMessageBO {

    private String title;

    private String content;

    private Map<String, Object> context;

    private List<AccountInfo> recipients;

    private List<MessageWay> ways;

    private String dingHook;

    private String httpPostEndpoint;

    private String wechatHook;

    private String feiShuHook;

    private List<MessageResult> resultList = new ArrayList<>();

}
