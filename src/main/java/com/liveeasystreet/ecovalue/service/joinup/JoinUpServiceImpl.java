package com.liveeasystreet.ecovalue.service.joinup;

import com.liveeasystreet.ecovalue.domain.JoinUp;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.JoinUpDto;
import com.liveeasystreet.ecovalue.repository.joinup.JoinUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinUpServiceImpl implements IJoinUpService {

    private final JoinUpRepository joinUpRepository;

    @Override
    public boolean joining(JoinUpDto joinUpDto) {

        JoinUp joinUp = joinUpRepository.isJoined(joinUpDto).orElse(null);

        if (joinUp == null) {
            joinUpRepository.joinUp(joinUpDto);

            return true;
        } else if (joinUp.isDeleted()) {
            joinUpRepository.updateJoinUp(joinUpDto);

            return true;
        } else {
            joinUpRepository.deleteJoin(joinUpDto);

            return false;
        }
    }
}
