package com.orders.orders.service;

import com.orders.orders.domain.Truck;
import com.orders.orders.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckService {
    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Truck CreateTruck(Truck truck){
        return truckRepository.save(truck);
    }

    public List<Truck> getAllTrucks(){
        return truckRepository.findAll();
    }
}
