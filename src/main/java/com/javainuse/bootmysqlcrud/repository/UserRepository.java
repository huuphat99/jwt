package com.javainuse.bootmysqlcrud.repository;

import com.javainuse.bootmysqlcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            select u from User u where u.username = :username
            and u.deleteFlg = false
            """)
    User getAccountByUsername(@Param("username") String username);
}
