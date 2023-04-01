package com.kuokyn.hotel.controller;

import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(
            value = "/register",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.POST})
    public long register(@RequestBody User user) {
        if(user == null || userRepository.findByPhone(user.getPhone()) != null) return -1;
        else {
            return userRepository.save(user).getId();
        }
    }

    @GetMapping("/login")
    public User login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        User user = userRepository.findByPhone(phone);
        if (user == null)
            return null;
        else if (!user.getPassword().equals(password))
            return null;
        else
            return user;
    }

    /*@PostMapping("/update")
    public void update(@RequestBody User user){
        if(userRepository.findById(user.getId()) != null) {
            userRepository.save(user);
        }

    }*/
}