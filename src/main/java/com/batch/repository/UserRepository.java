package com.batch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.model.User;
//import javax.sql.DataSource;
//import com.mysql.cj.jdbc.MysqlDataSource;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

