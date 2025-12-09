package com.example.yass.units.service;

import com.example.yass.units.dto.AirDto;
import com.example.yass.units.model.entity.Air;
import com.example.yass.units.model.utils.GpsCoordinates;
import com.example.yass.units.repository.AirRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AirService {

    private final AirRepository airRepository;

    AirService(AirRepository airRepository) {
        this.airRepository = airRepository;
    }

    @Transactional
    public List<Air> createAirUnits(List<AirDto> airUnits) {
        return airRepository.saveAll(fromAirDtoToAir(airUnits));
    }

    public Optional<AirDto> findAirUnitByUUID(UUID uuid) {

        Optional<Air> optionalAir = airRepository.findById(uuid);
        return optionalAir.map(air -> fromAirToAirDto(List.of(air)).get(0));
    }

    public List<AirDto> findAllAirUnitsByUUID(Set<UUID> uuids) {
        return fromAirToAirDto(airRepository.findAllById(uuids));
    }

    public List<AirDto> allAirUnits() {

        List<Air> airList = airRepository.findAll();
        return fromAirToAirDto(airList);
    }

    public void deleteAirUnitByUUID(UUID uuid) {
        airRepository.deleteById(uuid);
    }

    public void deleteAllAirUnits() {
        airRepository.deleteAll();
    }

    private List<AirDto> fromAirToAirDto(List<Air> airUnits) {

        return airUnits.stream().map(air -> AirDto.builder()
                        .id(air.getId())
                        .name(air.getName())
                        .speed(air.getSpeed())
                        .munitions(air.getMunitions())
                        .status(air.getStatus())
                        .latitude(air.getGpsCoordinates().getLatitude())
                        .longitude(air.getGpsCoordinates().getLongitude())
                        .altitude(air.getGpsCoordinates().getAltitude())
                        .type(air.getAirType())
                        .subordinates(air.getSubordinates())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Air> fromAirDtoToAir(List<AirDto> airUnits) {
        return airUnits.stream().map(airDto -> Air.builder()
                .id(airDto.id())
                .airType(airDto.type())
                .speed(airDto.speed())
                .munitions(airDto.munitions())
                .name(airDto.name())
                .status(airDto.status())
                .gpsCoordinates(new GpsCoordinates(airDto.latitude(), airDto.longitude(), airDto.altitude()))
                .subordinates(airDto.subordinates()).build()
        ).collect(Collectors.toList());
    }
}
