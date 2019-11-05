package cn.gotojava.webdev.filter;

import cn.gotojava.webdev.config.WebConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class IpFilter implements Filter {

    final static Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 获取请求ip
        String remoteAddr = request.getRemoteAddr();
        // 获取请求主机
        String remoteHost = request.getRemoteHost();
        // 获取请求端口
        int remotePort = request.getRemotePort();

        logger.info("请求IP："+remoteAddr);
        logger.info("请求主机："+remoteHost);
        logger.info("请求端口："+remotePort);

        // 过滤器放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
