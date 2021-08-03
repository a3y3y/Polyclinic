package by.it_academy.polyclinic.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}