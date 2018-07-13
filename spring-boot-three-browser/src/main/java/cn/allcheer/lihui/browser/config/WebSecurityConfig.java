package cn.allcheer.lihui.browser.config;

import cn.allcheer.lihui.browser.authentication.*;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *关于spring security 的配置
 * @author lihui 2017 12 26
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public WebSecurityConfig(SecurityAuthSuccessHandler securityAuthSuccessHandler
            , SecurityAuthFailHandler securityAuthFailHandler, HikariDataSource hikariDataSource){
        this.securityAuthFailHandler=securityAuthFailHandler;
        this.securityAuthSuccessHandler=securityAuthSuccessHandler;
        this.dataSource=hikariDataSource;
    }
    /**
     * 自定义的认证成功后的处理方法类
     */
    private SecurityAuthSuccessHandler securityAuthSuccessHandler;
    /**
     * 自定义的认证失败后的处理方法类
     */
    private SecurityAuthFailHandler securityAuthFailHandler;
    /**
     * 为了实现记住我功能
     */
    private HikariDataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 注册自定义的认证用户bean
     * @return 自定义的认证用户实现类
     */
    @Bean
    public CustomUserServiceAuthentication customUserService(){
        return new CustomUserServiceAuthentication();
    }
    /**
     * 自定义数据库用户信息认证提供者，重新设置其父类AbstractUserDetailsAuthenticationProvider
     * 中的hideUserNotFoundExceptions为false，解决了spring security 隐藏UsernameNotFoundException
     * 的异常信息
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(customUserService());
        return daoAuthenticationProvider;
    }
    /**
     * 将注册的自定义的认证用户加入到spring security
     * @param auth 认证管理者
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 配置访问的路径的权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(new LoginValidateFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            .antMatchers("/getValidateImageCode","/loginUrl","/myAuth/login").permitAll()
//          任何请求都需要认证
            .anyRequest().authenticated()
            .and()
//          定制登录行为
            .formLogin()
                .loginPage("/loginUrl")
                .loginProcessingUrl("/myAuth/login")
                .failureHandler(securityAuthFailHandler)
//               登录认证成功后的处理方法类
                .successHandler(securityAuthSuccessHandler)
                .and()
//                添加记住我功能
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(300)
                .userDetailsService(customUserService())
                .and()
//          定制注销行为
            .logout()
//          注销后清除认证信息
            .clearAuthentication(true);
    }
}
