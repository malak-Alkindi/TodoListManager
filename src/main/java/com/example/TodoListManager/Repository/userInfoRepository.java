package com.example.TodoListManager.Repository;

import com.example.TodoListManager.Models.userInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface userInfoRepository extends JpaRepository<userInfo, Long> {
    Optional<userInfo> findByUserName (String userName) ;
}
