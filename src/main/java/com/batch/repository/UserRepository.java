package com.batch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.batch.model.User;
//import javax.sql.DataSource;
//import com.mysql.cj.jdbc.MysqlDataSource;

public interface UserRepository extends JpaRepository<User, Integer> {

}

