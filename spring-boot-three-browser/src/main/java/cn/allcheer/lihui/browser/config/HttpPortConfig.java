package cn.allcheer.lihui.browser.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihui
 */
//@Configuration
public class HttpPortConfig {

//    @Value("${http.server.port}")
//    private Integer httpPort;
//    @Bean
//    public ServletWebServerFactory servletContainer(){
//        TomcatServletWebServerFactory tomcatServletWebServerFactory=new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createHttpConnector());
//        return tomcatServletWebServerFactory;
//    }
//
//    private Connector createHttpConnector(){
//        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(httpPort);
//        return connector;
//    }
}
