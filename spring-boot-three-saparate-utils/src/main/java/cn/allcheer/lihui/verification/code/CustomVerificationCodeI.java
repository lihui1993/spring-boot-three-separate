package cn.allcheer.lihui.verification.code;


import cn.allcheer.lihui.dal.domain.model.LoginImageCode;

/**
 * @author lihui
 */
public interface CustomVerificationCodeI {
    /**
     * 产生图片验证码
     * @return 返回图片验证码类
     */
    LoginImageCode createVerificationCode();
}
