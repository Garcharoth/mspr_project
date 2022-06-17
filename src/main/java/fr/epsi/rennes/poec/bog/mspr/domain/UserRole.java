package fr.epsi.rennes.poec.bog.mspr.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_EMPLOYEE, ROLE_SALESMAN, ROLE_CIO;

    @Override
    public String getAuthority() {
        return this.name();
    }

}

