package com.frro.bus.ticket.bus.service;

import com.frro.bus.ticket.bus.dto.BusRequest;
import com.frro.bus.ticket.bus.dto.BusResponse;
import com.frro.bus.ticket.bus.mapper.BusMapper;
import com.frro.bus.ticket.bus.model.Bus;
import com.frro.bus.ticket.bus.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
