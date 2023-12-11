package com.pos.demo.service;

import com.pos.demo.mapper.TaxMapper;
import com.pos.demo.model.dto.TaxDto;
import com.pos.demo.model.entity.TaxEntity;
import com.pos.demo.repository.TaxRepository;
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
public class TaxService {
    private final TaxRepository taxRepository;
    private final TaxMapper taxMapper;

    public TaxDto getTaxByID(UUID taxId) {
        log.info("Searching for tax with ID {}", taxId);
        Optional<TaxEntity> taxEntity = taxRepository.findById(taxId);
        if (taxEntity.isEmpty()) {
            log.warn("Tax was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tax was not found");
        }

        return taxMapper.entityToDto(taxEntity.get());
    }
}