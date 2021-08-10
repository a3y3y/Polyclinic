package com.it_academy.polyclinic.model.user_Info;

import org.springframework.security.core.GrantedAuthority;




public enum Role implements GrantedAuthority {
        PATIENT,
        DOCTOR,
        REGISTRATION_MANAGER,
        ADMIN;


        @Override
        public String getAuthority() {
                return name();
        }


}
