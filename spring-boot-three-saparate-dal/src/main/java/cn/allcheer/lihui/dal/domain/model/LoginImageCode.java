package cn.allcheer.lihui.dal.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDateTime;

/**
 * 图片验证码类
 * @author lihui
 */
@Getter
@Setter
public class LoginImageCode {
    private Image validateImage;
    private String validatecode;
    private LocalDateTime expireTime;

    /**
     * 图片验证码的构造函数
     * @param validateImage 图片
     * @param validatecode 图片中验证码信息
     * @param expireTimeSecond 过期时间，以秒为单位
     */
    public LoginImageCode(Image validateImage, String validatecode, int expireTimeSecond) {
        this.validateImage = validateImage;
        this.validatecode = validatecode;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTimeSecond);
    }
}
