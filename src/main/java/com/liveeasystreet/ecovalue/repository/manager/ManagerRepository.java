package com.liveeasystreet.ecovalue.repository.manager;

import com.liveeasystreet.ecovalue.domain.Manager;
import com.liveeasystreet.ecovalue.domain.Member;

import java.util.Optional;

public interface ManagerRepository {
    Optional<Manager> findByLoginId(String managerLoginId);
}
