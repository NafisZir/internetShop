package com.example.myShop.domain.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author nafis
 * @since 19.12.2021
 */

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
