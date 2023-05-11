package com.liveeasystreet.ecovalue.repository.manager;

import com.liveeasystreet.ecovalue.domain.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ManagerMapper {

    Optional<Manager> findByLoginId(String managerLoginId);
}
