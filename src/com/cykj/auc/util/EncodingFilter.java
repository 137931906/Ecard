package com.cykj.auc.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {



    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//      将请求和响应强制转换成Http形式
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取它的请求路径
        String requestURI = request.getRequestURI();
        //当检测到是以css后缀名结尾的时，直接放行
        if (requestURI.endsWith(".css")) {
//            System.out.println(".css");
            response.setContentType("text/css;charset=UTF-8");// 处理响应乱码
//            chain.doFilter(request, response);
        }
        else if (requestURI.endsWith(".js")) {
//            System.out.println(".js");
            response.setContentType("text/javascript;charset=UTF-8");// 处理响应乱码
//            chain.doFilter(request, response);
        }
        else {
            request.setCharacterEncoding("UTF-8");//将编码格式统一改为UTF-8[只对消息体有效]
            response.setContentType("text/html;charset=UTF-8");// 处理响应乱码

        }
        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
