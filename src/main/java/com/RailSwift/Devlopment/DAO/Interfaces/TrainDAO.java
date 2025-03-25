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

    public void updateTrainStops(Long trainNo, List<Stops> stops);

    public void deleteTrain(Long trainNo);

    public void addTrainStop(Long trainNo, Stops stop, int stopNo);

    public void deleteTrainStop(Long trainNo, int stopNo);

    public void updateTrainStop(Long trainNo, Stops stop, int stopNo);

    public List<Train> getListOfTrainBySrcDst(String src, String dst);
}
