package org.yug.societylink.security; // Suggested package move

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yug.societylink.model.Resident;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ResidentDetails implements UserDetails {

    private final Resident resident;

    public ResidentDetails(Resident resident) {
        this.resident = resident;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Splitting roles (e.g., "ROLE_RESIDENT,ROLE_ADMIN") into Authority objects
        return Arrays.stream(resident.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return resident.getPassword();
    }

    @Override
    public String getUsername() {
        // CRITICAL: Return the unique email, not the name!
        return resident.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}