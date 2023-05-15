package com.liveeasystreet.ecovalue.repository.manager;

import com.liveeasystreet.ecovalue.domain.Manager;
import com.liveeasystreet.ecovalue.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisManagerRepository implements ManagerRepository{

    private final ManagerMapper managerMapper;

    @Override
    public Optional<Manager> findByLoginId(String managerLoginId) {
        return managerMapper.findByLoginId(managerLoginId);
    }
}
