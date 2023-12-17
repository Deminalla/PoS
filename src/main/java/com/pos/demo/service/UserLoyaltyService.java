package com.pos.demo.service;

import com.pos.demo.model.dto.loyalty.UserLoyaltyDto;
import com.pos.demo.repository.UserLoyaltyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserLoyaltyService {
    private final UserLoyaltyRepository userLoyaltyRepository;

    public Integer getUserLoyaltyPointsById(UUID userId, UUID loyaltyId) {
        log.info("Searching for user's {} points for loyalty program {}", userId, loyaltyId);
        Optional<Integer> points = userLoyaltyRepository.findPointsByUserAndLoyaltyId(userId, loyaltyId);
        if (points.isEmpty()) {
            log.warn("User or loyalty program was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or loyalty program was not found");
        }

        return points.get();
    }

    public void updateUserLoyaltyPoints(UUID userId, UUID loyaltyId, Integer newPoints) {
        Integer points = getUserLoyaltyPointsById(userId, loyaltyId); // for validating whether it exists

        log.info("Updating user's {} points to {} for loyalty program {}", userId, newPoints, loyaltyId);

        userLoyaltyRepository.updatePoints(userId, loyaltyId, points + newPoints);
    }

    public List<UUID> getAllThisUserLoyaltyPrograms(UUID userId) {
        log.info("Searching for user's {} loyalty programs", userId);

        return userLoyaltyRepository.getUserLoyaltyPrograms(userId);
    }

    public UserLoyaltyDto addUserToLoyalty(UUID userId, UUID loyaltyId) {
        Optional<Integer> points = userLoyaltyRepository.findPointsByUserAndLoyaltyId(userId, loyaltyId);
        if (points.isPresent()) {
            log.warn("User is already associated with the loyalty program");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already associated with the loyalty program");
        }

        UUID randomId = UUID.randomUUID();
        UserLoyaltyDto userLoyaltyDto = new UserLoyaltyDto(randomId, loyaltyId, userId, 0);
        userLoyaltyRepository.addUserToLoyalty(userLoyaltyDto);

        return userLoyaltyDto;
    }
}
