package by.it_academy.polyclinic.config;



import by.it_academy.polyclinic.service.AuthProvider;
import by.it_academy.polyclinic.service.UserService;
import by.it_academy.polyclinic.storage.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Spring Framework dependencies and beans
 * @author Maksim Perekladov
 * @version 2.0
 */
@Configuration
@ComponentScan("by.it_academy.polyclinic.config")
public class RootConfig {


    @Bean
    public UserService userService(IUserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public AuthProvider authProvider(UserService userService){
        return new AuthProvider(userService);
    }

}
