package com.nashtech.training.shape.repository;

import com.nashtech.training.shape.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository <UserInfo,Long> {

    UserInfo findByUserName(String userName);

    UserInfo findById(Integer id);

    void deleteById(Integer id);
}
