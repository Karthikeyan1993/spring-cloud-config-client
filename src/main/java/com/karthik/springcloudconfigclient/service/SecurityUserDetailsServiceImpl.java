package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {

    private final UserProfileRepository userProfileRepository;

    public SecurityUserDetailsServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserProfile userProfile = this.userProfileRepository
                .findUserProfileByUsernameOrEmail(s, s)
                .orElseThrow(() -> new UsernameNotFoundException("Supplied Username Not Found"));

        User user = (User) User.builder()
                .username(userProfile.getUsername())
                .password(userProfile.getPassword())
                .authorities(new ArrayList<>())
                .build();
        return user;
    }
}
