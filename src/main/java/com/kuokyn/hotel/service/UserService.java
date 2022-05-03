package com.kuokyn.hotel.service;

import com.kuokyn.hotel.entity.Authority;
import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.exception.UserNotFoundException;
import com.kuokyn.hotel.filter.UserFilter;
import com.kuokyn.hotel.repository.AuthorityRepository;
import com.kuokyn.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Authority userRole = authorityRepository.findAuthorityByName("ROLE_USER");
        List roles = Arrays.asList(userRole);
        user.setAuthorities(new HashSet<>(roles));
        userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException(id));
    }

    public boolean isUniqueLogin(String username) {
        return userRepository.findByLogin(username) == null;
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
    public Page<User> getAllUsers(UserFilter search, Pageable pageable) {

        Page page;
        if (search.isEmpty()) {
            page = userRepository.findAll(pageable);
        } else {


            page = userRepository.findAllUsersUsingFilter(search.getPhraseLIKE(), pageable);
        }

        return page;
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
