package com.pos.demo.service;

import com.pos.demo.mapper.LoyaltyMapper;
import com.pos.demo.model.dto.loyalty.CreateLoyaltyDto;
import com.pos.demo.model.dto.loyalty.LoyaltyDto;
import com.pos.demo.model.entity.LoyaltyEntity;
import com.pos.demo.repository.LoyaltyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoyaltyService {
    private final LoyaltyRepository loyaltyRepository;
    private final LoyaltyMapper loyaltyMapper;

    public LoyaltyDto createLoyalty(CreateLoyaltyDto loyaltyDto) {
        log.info("Creating loyalty program {}", loyaltyDto);

        LoyaltyDto loyalty = loyaltyMapper.createToDto(loyaltyDto);
        UUID randomId = UUID.randomUUID();
        loyalty.setLoyaltyId(randomId);
        loyaltyRepository.createLoyalty(loyalty);
        return loyalty;
    }

    public LoyaltyDto findLoyalty(UUID loyaltyId) {
        log.info("Searching for loyalty program {}", loyaltyId);

        Optional<LoyaltyEntity> loyaltyEntity = loyaltyRepository.findById(loyaltyId);
        if (loyaltyEntity.isEmpty()) {
            log.warn("Loyalty program was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loyalty program was not found");
        }
        return loyaltyMapper.entityToDTo(loyaltyEntity.get());
    }
}
