package com.example.yass.units.controller;

import com.example.yass.units.dto.AirDto;
import com.example.yass.units.model.entity.Cavalry;
import com.example.yass.units.model.entity.Infantery;
import com.example.yass.units.service.AirService;
import com.example.yass.units.service.CavalryService;
import com.example.yass.units.service.InfanteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/units", produces = "application/json")
public class ArmyController {

    private final CavalryService cavalryService;
    private final InfanteryService infanteryService;
    private final AirService airService;

    ArmyController(CavalryService cavSrv, InfanteryService infSrv, AirService airSrv) {
        this.cavalryService = cavSrv;
        this.infanteryService = infSrv;
        this.airService = airSrv;
    }

    @GetMapping(path = "/armour-unit")
    public ResponseEntity<List<Cavalry>> armours() {
        return ResponseEntity.ok(cavalryService.allArmours());
    }

    @GetMapping(path = "/infantery-unit")
    public ResponseEntity<List<Infantery>> infantery() {
        return ResponseEntity.ok(infanteryService.allSoldiers());
    }

    @GetMapping(path = "/air-unit")
    public ResponseEntity<List<AirDto>> airUnits() {
        return ResponseEntity.ok(airService.allAirUnits());
    }
}
