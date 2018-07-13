package cn.allcheer.lihui.dal.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui
 */
@Setter
@ToString
@Getter
public class SimpleResponse {
    private Integer state;
    private String msg;
}
