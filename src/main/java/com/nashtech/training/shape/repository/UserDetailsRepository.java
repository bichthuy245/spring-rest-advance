package com.nashtech.training.shape.repository;

import com.nashtech.training.shape.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository <UserInfo,Long> {

    public UserInfo findByUserNameAndEnabled(String userName, short enabled);

    public List<UserInfo> findAllByEnabled(short enabled);

    public UserInfo findById(Integer id);

    public void deleteById(Integer id);
}
