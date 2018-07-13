package cn.allcheer.lihui.browser.config;

import cn.allcheer.lihui.verification.code.CustomVerificationCodeI;
import cn.allcheer.lihui.verification.code.server.CustomVerificationImageCodeImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
public class VerificationCodeConfig {
    @Bean
    @ConditionalOnMissingBean(name = "myVerificationCodeI")
    public CustomVerificationCodeI myVerificationCodeI(){
        CustomVerificationCodeI myVerificationCodeI=new CustomVerificationImageCodeImpl();
        return myVerificationCodeI;
    }
}
