package com.RailSwift.Devlopment.Service;

import com.RailSwift.Devlopment.DAO.Interfaces.TrainDAO;
import com.RailSwift.Devlopment.DTO.TrainDeatils;
import com.RailSwift.Devlopment.Entities.Stops;
import com.RailSwift.Devlopment.Entities.Train;
import com.RailSwift.Devlopment.Entities.TrainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {
    TrainDAO trainDAO;

    @Autowired
    public TrainService(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    public void addTrain(Train train) {
        trainDAO.addTrain(train);
    }

    public Train findTrainByTrainNo(Long trainNo) {
        return trainDAO.findTrainByTrainNo(trainNo);
    }

    public List<Train> findAllTrains() {
        return trainDAO.findAllTrains();
    }

    public void updateTrainName(Long trainNo, String trainName) {
        trainDAO.updateTrainName(trainNo, trainName);
    }

    public void updateTrainType(Long trainNo, TrainType trainType) {
        trainDAO.updateTrainType(trainNo, trainType);
    }

    public void updateTrainStops(Long trainNo, List<Stops> stops) {
        trainDAO.updateTrainStops(trainNo, stops);
    }

    public void deleteTrain(Long trainNo) {
        trainDAO.deleteTrain(trainNo);
    }

    public void addTrainStop(Long trainNo, Stops stop, int stopNo) {
        trainDAO.addTrainStop(trainNo, stop, stopNo);
    }

    public void deleteTrainStop(Long trainNo, int stopNo) {
        trainDAO.deleteTrainStop(trainNo, stopNo);
    }

    public void updateTrainStop(Long trainNo, Stops stop, int stopNo) {
        trainDAO.updateTrainStop(trainNo, stop, stopNo);
    }

    public List<TrainDeatils> getListOfTrainBySrcDst(String src, String dst) {
        List<Train> trains = trainDAO.getListOfTrainBySrcDst(src, dst);
        int srcStopOrder = -1, dstStopOrder = -1;
        List<TrainDeatils> trainDeatils = new ArrayList<>();
        for (Train train : trains) {
            for (Stops stop : train.getStopsList()) {
                if (stop.getStation().getStationName().equals(src)) {
                    srcStopOrder = stop.getStopNo();
                }
                if (stop.getStation().getStationName().equals(dst)) {
                    dstStopOrder = stop.getStopNo();
                }
            }
            if (srcStopOrder == -1 || dstStopOrder == -1 || srcStopOrder > dstStopOrder) {
                trains.remove(train);
            } else {
                trainDeatils.add(new TrainDeatils(train, train.getStopsList().get(srcStopOrder - 1).getStation(), train.getStopsList().get(dstStopOrder - 1).getStation(), train.getFarePerStops() * (dstStopOrder - srcStopOrder)));
            }
        }
        return trainDeatils;
    }

    public List<Train> getListOfTrainOnDate(LocalDate date) {
        List<Train> trainList = trainDAO.findAllTrains();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        for (Train train : trainList) {
            if (train.getActiveDaysList().stream().noneMatch(activeDays -> activeDays.getDay().equals(dayOfWeek))) {
                trainList.remove(train);
            }
        }
        return trainList;
    }

    public List<TrainDeatils> getListOfTrainBySrcDstAndDate(String src, String dst, LocalDate date) {
        List<TrainDeatils> trainDeatilsList = getListOfTrainBySrcDst(src, dst);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        for (TrainDeatils trainDeatils : trainDeatilsList) {
            if (trainDeatils.getTrain().getActiveDaysList().stream().noneMatch(activeDays -> activeDays.getDay().equals(dayOfWeek))) {
                trainDeatilsList.remove(trainDeatils);
            }
        }
        return trainDeatilsList;
    }
}
