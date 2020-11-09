package com.yi.demo.sample.repository;

import com.yi.demo.sample.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(Long uid);

}
