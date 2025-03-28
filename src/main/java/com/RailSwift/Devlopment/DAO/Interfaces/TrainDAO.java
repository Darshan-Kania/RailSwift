package com.RailSwift.Devlopment.DAO.Interfaces;

import com.RailSwift.Devlopment.Entities.Stops;
import com.RailSwift.Devlopment.Entities.Train;
import com.RailSwift.Devlopment.Entities.TrainType;

import java.util.List;

public interface TrainDAO {
    public void addTrain(Train train);

    public Train findTrainByTrainNo(Long trainNo);

    public List<Train> findAllTrains();

    public void updateTrainName(Long trainNo, String trainName);

    public void updateTrainType(Long trainNo, TrainType trainType);


    public void deleteTrain(Long trainNo);


    public List<Train> getListOfTrainBySrcDst(String src, String dst);

    public Train addTrainStops(Train train, List<Stops> stopsList);
}
