package com.example.yass.units.repository;

import com.example.yass.units.model.entity.Infantery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InfanteryRepository extends JpaRepository<Infantery, UUID> {
}
