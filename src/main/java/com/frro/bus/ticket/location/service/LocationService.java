package com.frro.bus.ticket.location.service;

import com.frro.bus.ticket.location.dto.LocationRequest;
import com.frro.bus.ticket.location.dto.LocationResponse;
import com.frro.bus.ticket.location.mapper.LocationMapper;
import com.frro.bus.ticket.location.model.Location;
import com.frro.bus.ticket.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    public List<LocationResponse> findAll() {
        return repository.findAll().stream().map(mapper::toLocationResponse).toList();
    }

    public Optional<LocationResponse> findById(Integer id) {
        return repository.findById(id).map(mapper::toLocationResponse);
    }

    public LocationResponse create(LocationRequest request) {
        Location entity = mapper.toLocation(request);
        Location saved = repository.save(entity);
        return mapper.toLocationResponse(saved);
    }

    public Optional<LocationResponse> update(Integer id, LocationRequest request) {
        return repository.findById(id).map(existing -> {
            Location toUpdate = mapper.toLocation(request);
            toUpdate.setId(id);
            Location saved = repository.save(toUpdate);
            return mapper.toLocationResponse(saved);
        });
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
