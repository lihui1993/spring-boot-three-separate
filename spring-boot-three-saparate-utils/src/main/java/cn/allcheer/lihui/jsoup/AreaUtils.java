package cn.allcheer.lihui.jsoup;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @author lihui
 */
@Slf4j
public class AreaUtils {

    private static final String DEFALUT_PID="100000000000";
    private static final Pattern PATTERN = Pattern.compile("^\\d{2,}$");

    public static String getCxhfByNationalBureauOfStat(String url) throws IOException {

        StringBuilder areaString=new StringBuilder("[\r\n");
        Document document= Jsoup.parse(new URL(url).openStream(),"GBK",url);
        Elements province_td_elements=document.getElementsByTag("table").select(".provincetable .provincetr td > a");
        for(Element province_td_element:province_td_elements){
            String absHref=province_td_element.attr("abs:href");
            String provinceId=StringUtils.rightPad(province_td_element.attr("href").substring(0,province_td_element.attr("href").indexOf(".")),12,"0");
            log.debug("\ntr--a:{}\ttext--:{}",absHref,province_td_element.text());
            areaString.append("{\"id\":\"")
                    .append(provinceId)
                    .append("\",\"name\":\"")
                    .append(province_td_element.text())
                    .append("\",\"pid\":\"")
                    .append(DEFALUT_PID)
                    .append("\"},\n");
            if(StringUtils.isNotBlank(absHref)){
                Document city =Jsoup.parse(new URL(absHref).openStream(),"GBK",absHref);
                Elements city_td_elements = city.getElementsByTag("table").select(".citytable .citytr td > a");
                String cityId=null;
                for(Element city_td_element:city_td_elements){
                    if(PATTERN.matcher(city_td_element.text()).find()){
                        cityId=city_td_element.text();
                        continue;
                    }
                    String absCityHref=city_td_element.attr("abs:href");
                    log.debug("\ncity_td---a:{}\ttext---:{}",absCityHref,city_td_element.text());
                    areaString.append("{\"id\":\"")
                            .append(cityId)
                            .append("\",\"name\":\"")
                            .append(city_td_element.text())
                            .append("\",\"pid\":\"")
                            .append(provinceId)
                            .append("\"},\n");
                    if(StringUtils.isNotBlank(absCityHref)){
                        Document county =Jsoup.parse(new URL(absHref).openStream(),"GBK",absHref);
                        Elements county_td_elements = county.getElementsByTag("table").select(".countytable .countytr td > a");
                        String countyId=null;
                        for(Element county_td_a_element:county_td_elements){
                            if(PATTERN.matcher(county_td_a_element.text()).find()){
                                countyId=county_td_a_element.text();
                                continue;
                            }
                            String countyAbsHref=county_td_a_element.attr("abs:href");
                            log.debug("\ncity_td---a:{}\ttext---:{}",countyAbsHref,county_td_a_element.text());
                            areaString.append("{\"id\":\"")
                                    .append(countyId)
                                    .append("\",\"name\":\"")
                                    .append(county_td_a_element.text())
                                    .append("\",\"pid\":\"")
                                    .append(cityId)
                                    .append("\"},\n");
                        }
                    }
                }
            }
        }
        areaString.replace(0,areaString.length()-1,StringUtils.removeEnd(areaString.toString(),",\n"));
        areaString.append("]");
        log.info("areaString:\n{}",areaString);
        return areaString.toString();
    }
}
