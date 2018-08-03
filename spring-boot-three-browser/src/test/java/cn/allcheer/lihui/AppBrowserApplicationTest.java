package cn.allcheer.lihui;

import static org.junit.Assert.assertTrue;

import cn.allcheer.lihui.jsoup.AreaUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Unit test for simple AppBrowserApplication.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppBrowserApplicationTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void getcxhfInfo(){
        try {
            AreaUtils.getCxhfByNationalBureauOfStat("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html");
        } catch (IOException e) {
            log.error("IOException：{}",e);
        }
    }
}
