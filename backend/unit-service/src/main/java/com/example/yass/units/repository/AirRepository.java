package com.example.yass.units.repository;

import com.example.yass.units.model.entity.Air;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirRepository extends JpaRepository<Air, UUID> {
}
