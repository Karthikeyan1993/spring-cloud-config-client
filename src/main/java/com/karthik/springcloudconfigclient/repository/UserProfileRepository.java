package com.karthik.springcloudconfigclient.repository;

import com.karthik.springcloudconfigclient.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileByUsernameOrEmail(String username, String email);

    UserProfile findUserProfileByUsername(String username);

    UserProfile findUserProfileByEmail(String email);

    List<UserProfile> findAllByIsActive(Boolean flag);
}
