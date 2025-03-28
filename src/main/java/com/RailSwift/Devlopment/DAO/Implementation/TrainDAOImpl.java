package com.RailSwift.Devlopment.DAO.Implementation;

import com.RailSwift.Devlopment.DAO.Interfaces.TrainDAO;
import com.RailSwift.Devlopment.Entities.Station;
import com.RailSwift.Devlopment.Entities.Stops;
import com.RailSwift.Devlopment.Entities.Train;
import com.RailSwift.Devlopment.Entities.TrainType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TrainDAOImpl implements TrainDAO {
    EntityManager entityManager;

    @Autowired
    public TrainDAOImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addTrain(Train train) {
        entityManager.persist(train);
    }

    @Override
    public Train findTrainByTrainNo(Long trainNo) {
        return entityManager.find(Train.class, trainNo);
    }

    @Override
    public List<Train> findAllTrains() {
        TypedQuery<Train> query = entityManager.createQuery("SELECT t FROM Train t", Train.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateTrainName(Long trainNo, String trainName) {
        Train train = entityManager.find(Train.class, trainNo);
        train.setTrainName(trainName);
        entityManager.merge(train);
    }

    @Override
    @Transactional
    public void updateTrainType(Long trainNo, TrainType trainType) {
        Train train = entityManager.find(Train.class, trainNo);
        train.setTrainType(trainType);
        entityManager.merge(train);
    }

    @Override
    @Transactional
    public void deleteTrain(Long trainNo) {
        Train train = entityManager.find(Train.class, trainNo);
        entityManager.remove(train);
    }

    @Override
    public List<Train> getListOfTrainBySrcDst(String src, String dst) {
        TypedQuery<Station> query1 = entityManager.createQuery("SELECT s FROM Station s WHERE s.stationName=:src", Station.class);
        query1.setParameter("src", src);
        Station source = query1.getSingleResult();
        TypedQuery<Station> query2 = entityManager.createQuery("SELECT s FROM Station s WHERE s.stationName=:dst", Station.class);
        query2.setParameter("dst", dst);
        Station destination = query2.getSingleResult();
        TypedQuery<Train> query3 = entityManager.createQuery(
                "SELECT t FROM Train t WHERE " +
                        "EXISTS (SELECT s FROM Stops s WHERE s MEMBER OF t.stopsList AND s.station.stationId = :source) " +
                        "AND EXISTS (SELECT s FROM Stops s WHERE s MEMBER OF t.stopsList AND s.station.stationId = :destination)",
                Train.class
        );
        query3.setParameter("source", source.getStationId()); // Assuming source is a Station object
        query3.setParameter("destination", destination.getStationId());

        return query3.getResultList();
    }


    @Override
    @Transactional
    public Train addTrainStops(Train train, List<Stops> stopsList) {
        for (Stops stop : train.getStopsList()) {
            entityManager.remove(stop);
        }
        train.setStopsList(stopsList);
        entityManager.merge(train);
        return train;
    }
}
