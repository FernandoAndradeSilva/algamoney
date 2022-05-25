package com.example.algamoney.api.cors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private String originPermitido = "http://localhost:8000"; // TODO: Configurar para diferentes ambientes

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {



        HttpServletRequest request  =  (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Acess-Control-Allow-Origin" , originPermitido);
        response.setHeader("Acess-Control-Allow-Credentials" , "true");

        if ("OPTIONS".equals(request.getMethod()) && originPermitido.equals(request.getHeader("Origin"))) {

            response.setHeader("Acess-Control-Allow-Methods" , "POST , GET, DELETE, PUT, OPTIONS");
            response.setHeader("Acess-Control-Allow-Headers" , "Authorization , Content-Type, Accept");
            response.setHeader("Acess-Control-Max-Age" , "3600");

            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
