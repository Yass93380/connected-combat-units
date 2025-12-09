package com.example.yass.units.service;

import com.example.yass.units.model.entity.Cavalry;
import com.example.yass.units.repository.CavalryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CavalryService {

    private final CavalryRepository cavalryRepository;

    CavalryService(CavalryRepository cavalryRepository) {
        this.cavalryRepository = cavalryRepository;
    }

    @Transactional
    public List<Cavalry> createAllArmours(Set<Cavalry> armourSet) {
        return cavalryRepository.saveAll(armourSet);
    }

    public Optional<Cavalry> findArmourByUUID(UUID uuid) {
        return cavalryRepository.findById(uuid);
    }

    public List<Cavalry> findAllArmoursByUUID(Set<UUID> uuids) {
        return cavalryRepository.findAllById(uuids);
    }

    public List<Cavalry> allArmours() {
        return cavalryRepository.findAll();
    }

    public void deleteArmourByUUID(UUID uuid) {
        cavalryRepository.deleteById(uuid);
    }

    public void deleteAllArmours() {
        cavalryRepository.deleteAll();
    }

}
