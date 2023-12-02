package com.csi;

import com.csi.model.User;
import com.csi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JwtcoreexApplication {

    @Autowired
    private UserRepository userRepositoryImpl;


    public static void main(String[] args) {
        SpringApplication.run(JwtcoreexApplication.class, args);
    }


    @PostConstruct
    public void saveUserData() {
        userRepositoryImpl.save(new User(121, "SWARA@FINTECH.COM", "SWARA@2025"));

    }

}
