package cn.allcheer.lihui.browser.controller;

import cn.allcheer.lihui.dal.domain.model.LoginImageCode;
import cn.allcheer.lihui.verification.code.CustomVerificationCodeI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * @author lihui
 */
@Controller
@Slf4j
public class WriterLoginImageController {


    private CustomVerificationCodeI customVerificationCodeI;

    @Autowired
    public WriterLoginImageController(CustomVerificationCodeI customVerificationCodeI){
        this.customVerificationCodeI = customVerificationCodeI;
    }

    @GetMapping("/getValidateImageCode")
    public void writerImageVolidataCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("请求到了图片验证码生成的headler");
        request.getSession().removeAttribute("loginVerificationImageCode");
        LoginImageCode loginImageCode= customVerificationCodeI.createVerificationCode();
        request.getSession().setAttribute("loginVerificationImageCode",loginImageCode);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ImageIO.write((RenderedImage) loginImageCode.getValidateImage(),"jpeg",response.getOutputStream());
        log.info("请求图片验证码生成结束");
    }
}
