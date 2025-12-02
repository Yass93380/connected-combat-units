package com.example.yass.units.repository;

import com.example.yass.units.model.entity.Cavalry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CavalryRepository extends JpaRepository<Cavalry, UUID> {
}
