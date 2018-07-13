package cn.allcheer.lihui.dal.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui 2017 12 19
 */
@Getter
@Setter
@ToString
public class Msg {
    private String title;
    private String content;
    private String etraInfo;

    public Msg(String title, String content, String etraInfo) {
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
}
