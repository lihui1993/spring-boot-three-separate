package cn.allcheer.lihui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 * @author lihui
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.allcheer.lihui")
public class AppBrowserApplication
{
    public static void main( String[] args ) {
        SpringApplication.run(AppBrowserApplication.class,args);
    }
}
