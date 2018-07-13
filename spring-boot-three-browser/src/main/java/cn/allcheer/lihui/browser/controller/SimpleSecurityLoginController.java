package cn.allcheer.lihui.browser.controller;

import cn.allcheer.lihui.dal.domain.model.Msg;
import cn.allcheer.lihui.dal.domain.model.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihui
 */
@RestController
@Slf4j
public class SimpleSecurityLoginController {

    @GetMapping("/")
    public Msg index(){
        Msg msg=new Msg("测试标题","测试内容","额外信息，显示");
        log.info("··········访问到了，给了model，返回index页面···");
        return msg;
    }
    @RequestMapping("/loginUrl")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleResponse doLoginPage(){
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setState(400);
        simpleResponse.setMsg("请先登录");
        return simpleResponse;
    }
}
