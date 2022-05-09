package com.kuokyn.hotel.config;

import com.kuokyn.hotel.entity.*;
import com.kuokyn.hotel.repository.AuthorityRepository;
import com.kuokyn.hotel.repository.RoomRepository;
import com.kuokyn.hotel.repository.RoomReservationRepository;
import com.kuokyn.hotel.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableJpaRepositories("com.kuokyn.hotel.repository")
public class RepositoriesInitializer {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    private final RoomReservationRepository roomReservationRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public RepositoriesInitializer(UserRepository userRepository,
                                   RoomRepository roomRepository,
                                   RoomReservationRepository roomReservationRepository,
                                   AuthorityRepository authorityRepository,
                                   PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.roomReservationRepository = roomReservationRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    InitializingBean init() {

        return () -> {

            if (authorityRepository.findAll().isEmpty()) {
                try {
                    Authority roleUser = authorityRepository.save(new Authority("ROLE_USER"));
                    Authority roleAdmin = authorityRepository.save(new Authority("ROLE_ADMIN"));

                    User test = new User("test");
                    test.setAuthorities(new HashSet<>(Arrays.asList(roleUser)));
                    test.setPassword(passwordEncoder.encode("test"));
                    test.setFirstName("Urszula");
                    test.setLastName("Kowalska");
                    test.setPhoneNumber("333333333");
                    test.setEmail("mail@mial.com");
                    User user = new User("user");
                    user.setAuthorities(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));
                    user.setFirstName("Tomasz");
                    user.setLastName("Momasz");
                    user.setEmail("tom@tp.pm");
                    user.setPhoneNumber("575839328");

                    User admin = new User("admin");
                    admin.setFirstName("Agnieszka");
                    admin.setLastName("Agnieszka");
                    admin.setEmail("aga@aga.aga");
                    admin.setPhoneNumber("7394638459");
                    admin.setAuthorities(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
