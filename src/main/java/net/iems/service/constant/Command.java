package net.iems.service.constant;

import lombok.*;

import java.io.Serializable;

/**
 * 客户端命令
 * 
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    CommandType type;

    String key;

    String value;

}
