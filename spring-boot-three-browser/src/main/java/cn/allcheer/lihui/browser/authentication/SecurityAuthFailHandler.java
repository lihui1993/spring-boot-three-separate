package cn.allcheer.lihui.browser.authentication;

import cn.allcheer.lihui.dal.domain.model.SimpleResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lihui
 */
@Component
public class SecurityAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        SimpleResponse simpleResponse=new SimpleResponse();
        simpleResponse.setState(300);
        if(exception instanceof BadCredentialsException){
            simpleResponse.setMsg("密码错误");
        }else {
            simpleResponse.setMsg(exception.getMessage());
        }
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write(JSONObject.toJSONString(simpleResponse));
        response.getWriter().close();
    }
}
