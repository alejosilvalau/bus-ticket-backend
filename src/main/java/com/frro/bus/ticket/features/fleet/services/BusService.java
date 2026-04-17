package com.frro.bus.ticket.features.fleet.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.frro.bus.ticket.features.fleet.entities.Bus;
import com.frro.bus.ticket.features.fleet.dtos.BusRequest;
import com.frro.bus.ticket.features.fleet.dtos.BusResponse;
import com.frro.bus.ticket.features.fleet.mappers.BusMapper;
import com.frro.bus.ticket.features.fleet.repositories.BusRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {
    private final BusRepository repository;
    private final BusMapper mapper;

    public List<BusResponse> findAll() {
        return repository.findAll().stream().map(mapper::toBusResponse).toList();
    }

    public Optional<BusResponse> findById(Integer id) {
        return repository.findById(id).map(mapper::toBusResponse);
    }

    public BusResponse create(BusRequest request) {
        Bus entity = mapper.toBus(request);
        Bus saved = repository.save(entity);
        return mapper.toBusResponse(saved);
    }

    public Optional<BusResponse> update(Integer id, BusRequest request) {
        return repository.findById(id).map(existing -> {
            Bus toUpdate = mapper.toBus(request);
            toUpdate.setId(id);
            Bus saved = repository.save(toUpdate);
            return mapper.toBusResponse(saved);
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
