package cn.allcheer.lihui.verification.code.server;

import cn.allcheer.lihui.customporperties.CustomConfigurationProperties;
import cn.allcheer.lihui.dal.domain.model.LoginImageCode;
import cn.allcheer.lihui.verification.code.CustomVerificationCodeI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author lihui
 */
@Slf4j
public class CustomVerificationImageCodeImpl implements CustomVerificationCodeI {

    @Autowired
    private CustomConfigurationProperties customConfigurationProperties;

    @Override
    public LoginImageCode createVerificationCode() {
        int width= customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeWidth();
        int height= customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeHeight();
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics=bufferedImage.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200, 250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        graphics.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer sRand = new StringBuffer();

        for (int i = 0; i < customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeLength();) {
            char c= (char) random.nextInt(122);
            if( !((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122 )) ){
                continue;
            }
            log.info("c is ---{}",String.valueOf(c));
            if(StringUtils.hasText( String.valueOf(c).trim() )){
                sRand.append(c);
                graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                graphics.drawString(String.valueOf(c), customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeXBaseCoordinate() * i + 36, customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeYCoordinate());
                ++i;
            }
        }

        graphics.dispose();
        return  new LoginImageCode(bufferedImage,sRand.toString(), customConfigurationProperties.getCustomVerificationCode().getCustomImageVerificationCode().getLoginImageCodeExpireTimeSecond());
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
