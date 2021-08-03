package by.it_academy.polyclinic.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyMultiPartFilter extends MultipartFilter {

    Logger logger = LoggerFactory.getLogger(MyMultiPartFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        request.getParameterNames();

        super.doFilterInternal(request, response, filterChain);
    }
}