package com.shopping.shoppingCartApp.repositories;

import com.shopping.shoppingCartApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select usr from user usr where usr.userName= :userName")
    User findByUserName(final @Param("userName") String userName);
}
