package com.ybzn.yeb.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
public class ChatMsg {

    private String from;

    private String to;

    private String content;

    private LocalDateTime date;

    private String fromNickName;

}
