package cn.gotojava.webdev.configuration;

import cn.gotojava.webdev.filter.IpFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration{

    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistration(){
        // 获取过滤器注入bean对象
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 将过滤器加入过滤器链
        registration.setFilter(new IpFilter());
        // 过滤所有url请求
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName","paramValue");
        registration.setName("customerFilter");
        registration.setOrder(1);
        return registration;
    }

}
