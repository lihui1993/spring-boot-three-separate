package cn.allcheer.lihui.customporperties.verification.code;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihui
 */
@Setter
@Getter
@ToString
public class CustomImageVerificationCode {
    /**
     * 验证码图片的宽度
     */
    private int loginImageCodeWidth=200;
    /**
     * 验证码图片的高度
     */
    private int loginImageCodeHeight=50;
    /**
     * 验证码有多少位(个)
     */
    private int loginImageCodeLength=4;
    /**
     * 单个验证码所在位置的x坐标基础值，将该值乘以这单个验证码所在下标，再+36
     */
    private int loginImageCodeXBaseCoordinate=43;
    /**
     * 整体验证码所在位置的y坐标
     */
    private int loginImageCodeYCoordinate=36;
    /**
     * 图片验证码的过期时间，以秒为单位
     */
    private int loginImageCodeExpireTimeSecond=120;
}
