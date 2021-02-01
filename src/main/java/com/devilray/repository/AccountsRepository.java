package com.devilray.repository;


import com.devilray.model.Accounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository <Accounts, Long> {

    String kewordSearch="SELECT u FROM Accounts u WHERE u.email =:keyword OR u.fName LIKE (CONCAT('%',:keyword, '%')) OR u.lName =:keyword";

    Optional<Accounts> findByEmail(String email);

    @Query("FROM Accounts WHERE email=?1 AND password=?2")
    Optional<Accounts> login(String email, String password);

    @Query("FROM Accounts WHERE fName = ?1 OR lName=?1 OR email=?2")
    List<Accounts> search(String fname, String email);


    @Query("SELECT a FROM Accounts a WHERE a.email =:email AND " +
            "a.fName LIKE (CONCAT('%',:name, '%')) OR a.lName =:name")
    List<Accounts> customeseachUser(@Param("name") String name, @Param("email") String email);

    @Query(kewordSearch)
    Page<Accounts> customeseacher(@Param("keyword") String keyword, Pageable pageable);

}

