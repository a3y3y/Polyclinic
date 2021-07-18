package by.it_academy.polyclinic.config;

import by.it_academy.polyclinic.service.AuthProvider;
import by.it_academy.polyclinic.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring controllers config
 * @author Maksim Perekladov
 * @version 2.0
 */
@Configuration
@ComponentScan("by.it_academy.polyclinic.controller")
public class WebConfig implements WebMvcConfigurer {

}
