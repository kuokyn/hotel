package com.kuokyn.hotel.service;

import com.kuokyn.hotel.entity.Authority;
import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.entity.Worker;
import com.kuokyn.hotel.exception.UserNotFoundException;
import com.kuokyn.hotel.filter.UserFilter;
import com.kuokyn.hotel.filter.WorkerFilter;
import com.kuokyn.hotel.repository.AuthorityRepository;
import com.kuokyn.hotel.repository.RoomReservationRepository;
import com.kuokyn.hotel.repository.WorkerRepository;
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
public class WorkerService {
    
    @Autowired
    WorkerRepository workerRepository;
    
    public void save(Worker worker) {
        workerRepository.save(worker);
    }
    public Worker getWorker(Long id) {
        return workerRepository.findById(id).orElseThrow();
    }

    public Page<Worker> getAllWorkers(WorkerFilter search, Pageable pageable) {
        Page<Worker> page;
        if (search.isEmpty()) {
            page = workerRepository.findAll(pageable);
        } else {
            page = workerRepository.findAllWorkersUsingFilter(search.getPhraseLIKE(), pageable);
        }
        return page;

    }

    @Transactional
    public void deleteWorker(Long id) {
        if (workerRepository.existsById(id)) {
            workerRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

}
