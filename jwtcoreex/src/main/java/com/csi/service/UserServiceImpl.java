package com.csi.service;

import com.csi.model.User;
import com.csi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepositoryImpl;

    public User saveData(User user) {
        return userRepositoryImpl.save(user);
    }

    public Optional<User> findById(int userId) {
        return userRepositoryImpl.findById(userId);
    }

    public List<User> findAll() {
        return userRepositoryImpl.findAll();
    }

    public User updateData(User user) {
        return userRepositoryImpl.save(user);
    }

    public void deleteById(int userId) {
        userRepositoryImpl.deleteById(userId);
    }

    public void deleteAll() {
        userRepositoryImpl.deleteAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepositoryImpl.findByUserName(username);

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new ArrayList<>());
    }
}
