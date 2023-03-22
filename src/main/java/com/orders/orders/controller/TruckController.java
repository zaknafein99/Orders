package com.orders.orders.controller;

import com.orders.orders.domain.Truck;
import com.orders.orders.service.TruckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public List<Truck> getAllTrucks(){
        return truckService.getAllTrucks();
    }

    @PostMapping("")
    public Truck createTruck(@RequestBody Truck truck){
        return truckService.CreateTruck(truck);
    }
}
