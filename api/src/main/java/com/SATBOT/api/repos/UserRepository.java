package com.SATBOT.api.repos;

import com.SATBOT.api.entities.Role;
import com.SATBOT.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    public User findByEmailAndPassword(String email,String password);

    @Query(value = "select r.auth_role_id, r.role_name, r.role_desc from auth_role r inner join auth_user_role aur on r.auth_role_id = aur.auth_role_id inner join user u on r.auth_role_id = u.id where u.id=?1;", nativeQuery = true )
    public Role findRoleByUserId(Long id);

}
