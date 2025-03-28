package com.RailSwift.Devlopment.DAO.Interfaces;

import com.RailSwift.Devlopment.Entities.Station;

import java.util.List;

public interface StationDAO {
    public Station addStation(Station station);

    public List<Station> addMultipleStations(List<Station> stations);

    public Station getStation(int stationId);

    public Station getStationByName(String stationName);

    public Station updateStationName(int stationId, String stationName);

    public Station updateStationCode(int stationId, String stationCode);

    public Station updateCity(int stationId, String city);

    public void deleteStation(int stationId);
}
