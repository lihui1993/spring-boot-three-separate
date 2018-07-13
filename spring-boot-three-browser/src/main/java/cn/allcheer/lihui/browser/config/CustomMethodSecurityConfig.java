package cn.allcheer.lihui.browser.config;

import cn.allcheer.lihui.browser.authentication.CustomPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 这个是作用在方法的注解上使用自定义的@hasPermision()
 * 一般用在Controller的方法上
 * @author lihui
 */
@EnableGlobalMethodSecurity
public class CustomMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private CustomPermissionEvaluator customPermissionEvaluator;

    @Autowired
    public CustomMethodSecurityConfig(CustomPermissionEvaluator customPermissionEvaluator) {
        this.customPermissionEvaluator = customPermissionEvaluator;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler=new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(customPermissionEvaluator);
        return defaultMethodSecurityExpressionHandler;
    }

}
