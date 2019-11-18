package com.library.system.repositories;

import com.library.system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    @Query(value = "select * from user as u where u.user_type='FOREIGN' and u.is_reg = 0",nativeQuery = true)
    List<User> findUnregForeign();
}
