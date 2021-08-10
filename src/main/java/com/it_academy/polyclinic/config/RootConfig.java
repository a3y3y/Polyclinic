package com.it_academy.polyclinic.config;



import com.it_academy.polyclinic.service.*;
import com.it_academy.polyclinic.service.api.*;
import com.it_academy.polyclinic.storage.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Spring Framework dependencies and beans
 * @author Maksim Perekladov
 * @version 2.0
 */
@Configuration
@ComponentScan("com.it_academy.polyclinic.config")
public class RootConfig {


    @Bean
    public IUserService userService(IUserRepository userRepository,
                                    IDepartmentRepository departmentRepository,
                                    ISpecializationRepository specializationRepository,
                                    IDoctorInfoRepository doctorInfoRepository){
        return new UserService(userRepository, departmentRepository, specializationRepository, doctorInfoRepository);
    }

    @Bean
    public IMedicalNoteService medicalNoteService(IMedicalNoteRepository medicalNoteRepository){
        return new MedicalNoteService(medicalNoteRepository);
    }

    @Bean
    public IPassportService passportService(IPassportRepository passportRepository){
        return new PassportService(passportRepository);
    }

    @Bean
    public AuthProvider authProvider(IUserService userService){
        return new AuthProvider(userService);
    }

    @Bean
    public IAddressService addressService(IAddressRepository addressRepository){
        return new AddressService(addressRepository);
    }

    @Bean
    public IDoctorInfoService doctorInfoService(IDoctorInfoRepository doctorInfoRepository){
        return new DoctorInfoService(doctorInfoRepository);
    }

    @Bean
    public ISpecializationService specializationService(ISpecializationRepository specializationRepository){
        return new SpecializationService(specializationRepository);
    }

    @Bean
    public IDepartmentService departmentService(IDepartmentRepository departmentRepository){
        return new DepartmentService(departmentRepository);
    }

    @Bean
    public ITicketService ticketService(ITicketRepository ticketRepository, IUserRepository userRepository){
        return new TicketService(ticketRepository, userRepository);
    }
}
