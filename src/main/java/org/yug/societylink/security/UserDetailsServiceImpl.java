package org.yug.societylink.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yug.societylink.exception.UserNotFoundException;
import org.yug.societylink.model.Resident;
import org.yug.societylink.repository.ResidentRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ResidentRepository residentRepository;
    public UserDetailsServiceImpl(ResidentRepository residentRepository){
        this.residentRepository=residentRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident=residentRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("USER WITH EMAIL "+username+" DOESN'T EXIST"));
        return new ResidentDetails(resident);
    }
}
