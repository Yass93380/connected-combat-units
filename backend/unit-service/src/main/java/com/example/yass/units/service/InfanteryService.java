package com.example.yass.units.service;

import com.example.yass.units.model.entity.Infantery;
import com.example.yass.units.repository.InfanteryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class InfanteryService {

    InfanteryRepository infanteryRepository;

    InfanteryService(InfanteryRepository repository) {
        this.infanteryRepository = repository;
    }

    @Transactional
    public List<Infantery> createAllSoldiers(Set<Infantery> infanterySet) {
        return infanteryRepository.saveAll(infanterySet);
    }

    public Optional<Infantery> findSoldierByUUID(UUID uuid) {
        return infanteryRepository.findById(uuid);
    }

    public List<Infantery> findAllSoldiersByUUID(Set<UUID> uuids) {
        return infanteryRepository.findAllById(uuids);
    }

    public List<Infantery> allSoldiers() {
        return infanteryRepository.findAll();
    }

    public void deleteSoldierByUUID(UUID uuid) {
        infanteryRepository.deleteById(uuid);
    }

    public void deleteAllSoldiers() {
        infanteryRepository.deleteAll();
    }
}
