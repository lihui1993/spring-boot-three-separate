package cn.allcheer.lihui.browser.authentication;

import cn.allcheer.lihui.dal.domain.model.LoginImageCode;
import cn.allcheer.lihui.dal.domain.model.SimpleResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author lihui
 */
@Slf4j
public class LoginValidateFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("url:{}",httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().equals("/myAuth/login")){
            LoginImageCode loginImageCode = (LoginImageCode) httpServletRequest.getSession().getAttribute("loginVerificationImageCode");
            String inputValidateCode = httpServletRequest.getParameter("validateImageCode");
            log.info("-----开始校验图片验证码----");
            log.info("-----用户输入：{}--系统中：{}",inputValidateCode,loginImageCode.getValidatecode());
            if(checkTimeOut(loginImageCode) && checkValidateCode(loginImageCode,inputValidateCode)){
                log.info("----校验图片验证码通过----");
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }else{
                log.error("----校验图片验证码未通过----");
                SimpleResponse simpleResponse=new SimpleResponse();
                simpleResponse.setState(300);
                simpleResponse.setMsg("验证码错误");
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
                httpServletResponse.getWriter().write(JSONObject.toJSONString(simpleResponse));
                httpServletResponse.getWriter().close();
            }
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    private boolean checkTimeOut(LoginImageCode loginImageCode){
        LocalDateTime localDateTimeNow=LocalDateTime.now();
        LocalDateTime localDateTimeExpire = loginImageCode.getExpireTime();
        if(localDateTimeNow.isBefore(localDateTimeExpire)){
            log.info("图片验证码未过期");
            return true;
        }else {
            log.info("图片验证码已过期");
            return false;
        }
    }

    private boolean checkValidateCode(LoginImageCode loginImageCode,String inputValidateCode){
        if(StringUtils.hasText( inputValidateCode ) ){
            if(loginImageCode.getValidatecode().trim().equals(inputValidateCode.trim())){
                log.info("图片验证码字符校验通过");
                return true;
            }
        }
        log.info("图片验证码字符校验未通过");
        return false;
    }
}
