package com.frro.bus.ticket.trip.service;

import com.frro.bus.ticket.trip.dto.TripRequest;
import com.frro.bus.ticket.trip.dto.TripResponse;
import com.frro.bus.ticket.trip.mapper.TripMapper;
import com.frro.bus.ticket.trip.model.Trip;
import com.frro.bus.ticket.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository repository;
    private final TripMapper mapper;

    public List<TripResponse> findAll() {
        return repository.findAll().stream().map(mapper::toTripResponse).toList();
    }

    public Optional<TripResponse> findById(Integer id) {
        return repository.findById(id).map(mapper::toTripResponse);
    }

    public TripResponse create(TripRequest request) {
        Trip entity = mapper.toTrip(request);
        Trip saved = repository.save(entity);
        return mapper.toTripResponse(saved);
    }

    public Optional<TripResponse> update(Integer id, TripRequest request) {
        return repository.findById(id).map(existing -> {
            Trip toUpdate = mapper.toTrip(request);
            toUpdate.setId(id);
            Trip saved = repository.save(toUpdate);
            return mapper.toTripResponse(saved);
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
