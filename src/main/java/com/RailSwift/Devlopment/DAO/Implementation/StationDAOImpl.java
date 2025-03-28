package com.RailSwift.Devlopment.DAO.Implementation;

import com.RailSwift.Devlopment.DAO.Interfaces.StationDAO;
import com.RailSwift.Devlopment.Entities.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class StationDAOImpl implements StationDAO {
    EntityManager entityManager;

    @Autowired
    public StationDAOImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Station addStation(Station station) {
        entityManager.persist(station);
        return station;
    }

    @Override
    @Transactional
    public List<Station> addMultipleStations(List<Station> stations) {
        for (Station station : stations) {
            entityManager.persist(station);
        }
        return stations;
    }

    @Override
    public Station getStation(int stationId) {
        Station st = entityManager.find(Station.class, stationId);
        return st;
    }

    @Override
    public Station getStationByName(String stationName) {
        TypedQuery<Station> stationTypedQuery = entityManager.createQuery("SELECT s FROM Station s WHERE s.stationName=:stationName", Station.class);
        stationTypedQuery.setParameter("stationName", stationName);
        return stationTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public Station updateStationName(int stationId, String stationName) {
        Station st = entityManager.find(Station.class, stationId);
        st.setStationName(stationName);
        entityManager.merge(st);
        return st;
    }

    @Override
    @Transactional
    public Station updateStationCode(int stationId, String stationCode) {
        Station st = entityManager.find(Station.class, stationId);
        st.setStationCode(stationCode);
        entityManager.merge(st);
        return st;
    }

    @Override
    @Transactional
    public Station updateCity(int stationId, String city) {
        Station st = entityManager.find(Station.class, stationId);
        st.setCity(city);
        entityManager.merge(st);
        return st;
    }

    @Override
    @Transactional
    public void deleteStation(int stationId) {
        Station st = entityManager.find(Station.class, stationId);
        entityManager.remove(st);
    }
}
