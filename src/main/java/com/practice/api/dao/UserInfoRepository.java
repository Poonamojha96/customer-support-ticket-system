package com.practice.api.dao;

import com.practice.api.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing UserInfo entities.
 * Extends JpaRepository to provide CRUD operations for UserInfo entities.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    /**
     * Finds a UserInfo entity by its username.
     *
     * @param username the username to search for
     * @return an Optional containing the UserInfo entity if found, or empty if not
     */
    Optional<UserInfo> findByName(String username);
}
