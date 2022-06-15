package com.batch.processor;

import com.batch.model.User;
import com.batch.repository.UserRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBItemWriter implements ItemWriter<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends User> user1) throws Exception {

        System.out.println("Data Saved for Users: " + user1);
        userRepository.saveAll(user1);
    }
}
