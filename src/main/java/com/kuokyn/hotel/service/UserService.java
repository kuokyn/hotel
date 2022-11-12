package com.kuokyn.hotel.service;

import com.kuokyn.hotel.entity.Authority;
import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.exception.UserNotFoundException;
import com.kuokyn.hotel.filter.UserFilter;
import com.kuokyn.hotel.repository.AuthorityRepository;
import com.kuokyn.hotel.repository.RoomReservationRepository;
import com.kuokyn.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoomReservationRepository roomReservationRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roomReservationRepository = roomReservationRepository;
    }


    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Authority userRole = authorityRepository.findAuthorityByTitle("ROLE_USER");
        List<Authority> roles = Arrays.asList(userRole);
        System.out.println("################# " + userRole.toString());
        user.setAuthorities(new HashSet<>(roles));
        userRepository.save(user);
    }

    public void editUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException(id));
    }


    public boolean isUniquePhone(String phone) {
        return userRepository.findByPhone(phone) == null;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Page<User> getAllUsers(UserFilter search, Pageable pageable) {
        Page<User> page;
        if (search.isEmpty()) {
            page = userRepository.findAll(pageable);
        } else {
            page = userRepository.findAllUsersUsingFilter(search.getPhraseLIKE(), pageable);
        }
        return page;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            if (!roomReservationRepository.findRoomReservationByUserId(id).isEmpty()) {
                roomReservationRepository.deleteAllByUserId(id);
            }
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}
