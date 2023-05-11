package com.liveeasystreet.ecovalue.service.manager;

import com.liveeasystreet.ecovalue.domain.Manager;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.repository.manager.ManagerRepository;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerLoginService {

    private final ManagerRepository managerRepository;


    public Manager login(String loginId, String password, String secondPassword) {
        Optional<Manager> findManagerOptional = managerRepository.findByLoginId(loginId);
        Manager manager = findManagerOptional.get();
        if (manager.getManagerPassword().equals(password)){
            if (manager.getSecondManagerPassword().equals(secondPassword)){
                return manager;
            }
            else {
                return null;
            }
        }else {
            return null;
        }
    }
}
