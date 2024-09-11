package com.ox.user.repositories;

import com.ox.user.entities.User;
import com.ox.user.entities.UserTh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThRepository extends JpaRepository<UserTh, Long> {
    UserTh findByUsername(String username);
}
