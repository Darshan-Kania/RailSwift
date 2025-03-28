package com.RailSwift.Devlopment.Controller;

import com.RailSwift.Devlopment.Entities.Station;
import com.RailSwift.Devlopment.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("RailSwift/Station")
public class StationController {
    StationService stationService;

    @Autowired
    public StationController(final StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/getStation/{stationId}")
    public Station getStation(@PathVariable int stationId) {
        return stationService.getStationById(stationId);
    }

    @GetMapping("/getStationByName/{stationName}")
    public Station getStationByName(@PathVariable String stationName) {
        return stationService.getStationByName(stationName);
    }

    @PostMapping("/addStation")
    public Station addStation(@RequestBody Station station) {
        return stationService.addStation(station);
    }

    @PostMapping("/addMultipleStations")
    public List<Station> addMultipleStations(@RequestBody List<Station> stations) {
       return stationService.addMultipleStation(stations);
    }

    @PutMapping("/updateStationName/{stationId}/{stationName}")
    public Station updateStationName(@PathVariable int stationId, @PathVariable String stationName) {
        return stationService.updateStationName(stationId, stationName);
    }

    @PutMapping("/updateStationCode/{stationId}/{stationCode}")
    public Station updateStationCode(@PathVariable int stationId, @PathVariable String stationCode) {
        return stationService.updateStationCode(stationId, stationCode);
    }

    @PutMapping("/updateCity/{stationId}/{city}")
    public Station updateCity(@PathVariable int stationId, @PathVariable String city) {
        return stationService.updateCity(stationId, city);
    }

    @DeleteMapping("/deleteStation/{stationId}")
    public void deleteStation(@PathVariable int stationId) {
        stationService.deleteStation(stationId);
    }
}
