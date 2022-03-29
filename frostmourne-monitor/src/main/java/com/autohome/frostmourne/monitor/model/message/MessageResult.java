package com.autohome.frostmourne.monitor.model.message;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 消息发送结果
 *
 * @author Aping
 * @since 2022/3/28 13:20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult {

    private MessageWay way;

    private Boolean success;

}