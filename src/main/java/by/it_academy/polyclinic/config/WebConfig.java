package by.it_academy.polyclinic.config;

import by.it_academy.polyclinic.service.AuthProvider;
import by.it_academy.polyclinic.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring controllers config
 * @author Maksim Perekladov
 * @version 2.0
 */
@Configuration
@ComponentScan("by.it_academy.polyclinic.controller")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter(ObjectMapper mapper){
        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        return new StringHttpMessageConverter();
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
