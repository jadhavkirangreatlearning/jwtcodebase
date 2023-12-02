package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.AuthRequest;
import com.csi.model.User;
import com.csi.service.UserServiceImpl;
import com.csi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.StartTlsResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/save")
    public ResponseEntity<User> saveData(@RequestBody User user){
        return ResponseEntity.ok(userServiceImpl.saveData(user));
    }


    @GetMapping("/findbyid/{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable int userId){
        return ResponseEntity.ok(userServiceImpl.findById(userId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @GetMapping("/sortbyusername")
    public ResponseEntity<List<User>> sortByUserName(){
        return ResponseEntity.ok(userServiceImpl.findAll().stream().sorted(Comparator.comparing(User::getUserName)).toList());
    }

    @PutMapping("/updatedata/{userId}")
    public ResponseEntity<User> updateData(@PathVariable int userId, @RequestBody User user){


        User user1 = userServiceImpl.findById(userId).orElseThrow(()-> new RecordNotFoundException("User ID Does Not Exist"));

        user1.setUserName(user.getUserName());
        user1.setUserPassword(user.getUserPassword());

        return ResponseEntity.ok(userServiceImpl.updateData(user1));
    }

    @DeleteMapping("/deletebytid/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable int userId){
        userServiceImpl.deleteById(userId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData(){
        userServiceImpl.deleteAll();

        return ResponseEntity.ok("ALL Data Deleted Successfully");
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getUserPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Incorrect un/pwd");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
