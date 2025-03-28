package com.RailSwift.Devlopment.Service;

import com.RailSwift.Devlopment.DAO.Interfaces.StationDAO;
import com.RailSwift.Devlopment.Entities.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    StationDAO stationDAO;

    @Autowired
    public StationService(final StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public Station addStation(Station station) {
        return stationDAO.addStation(station);
    }

    public List<Station> addMultipleStation(List<Station> stations) {
        return stationDAO.addMultipleStations(stations);
    }

    public Station getStationById(int stationId) {
        return stationDAO.getStation(stationId);
    }

    public Station getStationByName(String stationName) {
        return stationDAO.getStationByName(stationName);
    }

    public Station updateStationName(int stationId, String stationName) {
        return stationDAO.updateStationName(stationId, stationName);
    }

    public Station updateStationCode(int stationId, String stationCode) {
        return stationDAO.updateStationCode(stationId, stationCode);
    }

    public Station updateCity(int stationId, String city) {
        return stationDAO.updateCity(stationId, city);
    }

    public void deleteStation(int stationId) {
        stationDAO.deleteStation(stationId);
    }
}
