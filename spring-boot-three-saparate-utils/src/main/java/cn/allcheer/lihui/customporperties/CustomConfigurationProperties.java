package cn.allcheer.lihui.customporperties;

import cn.allcheer.lihui.customporperties.verification.code.CustomVerificationCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@ConfigurationProperties(prefix = "my.configuration")
@PropertySource(value = "classpath:/customApplication.properties")
@Component
@Setter
@Getter
@ToString
public class CustomConfigurationProperties {
   private CustomVerificationCode customVerificationCode;
}
