package com.RailSwift.Devlopment.Service;

import com.RailSwift.Devlopment.DAO.Interfaces.StationDAO;
import com.RailSwift.Devlopment.DAO.Interfaces.TrainDAO;
import com.RailSwift.Devlopment.DTO.StopDetails;
import com.RailSwift.Devlopment.DTO.TrainDeatils;
import com.RailSwift.Devlopment.Entities.Stops;
import com.RailSwift.Devlopment.Entities.Train;
import com.RailSwift.Devlopment.Entities.TrainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TrainService {
    TrainDAO trainDAO;
    StationDAO stationDAO;

    @Autowired
    public TrainService(TrainDAO trainDAO, StationDAO stationDAO) {
        this.trainDAO = trainDAO;
        this.stationDAO = stationDAO;
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


    public void deleteTrain(Long trainNo) {
        trainDAO.deleteTrain(trainNo);
    }


    public List<TrainDeatils> getListOfTrainBySrcDst(String src, String dst) {
        List<Train> trains = trainDAO.getListOfTrainBySrcDst(src, dst);
        List<TrainDeatils> trainDeatils = new ArrayList<>();

        Iterator<Train> iterator = trains.iterator();
        while (iterator.hasNext()) {
            Train train = iterator.next();
            int srcStopOrder = -1, dstStopOrder = -1;

            for (Stops stop : train.getStopsList()) {
                if (stop.getStation().getStationName().equals(src)) {
                    srcStopOrder = stop.getStopNo();
                }
                if (stop.getStation().getStationName().equals(dst)) {
                    dstStopOrder = stop.getStopNo();
                }
            }

            if (srcStopOrder == -1 || dstStopOrder == -1 || srcStopOrder > dstStopOrder) {
                iterator.remove(); // Safe removal
            } else {
                trainDeatils.add(new TrainDeatils(
                        train,
                        train.getStopsList().get(srcStopOrder - 1).getStation(),
                        train.getStopsList().get(dstStopOrder - 1).getStation(),
                        train.getFarePerStops() * (dstStopOrder - srcStopOrder)
                ));
            }
        }
        return trainDeatils;
    }

    public List<Train> getListOfTrainOnDate(LocalDate date) {
        List<Train> trainList = trainDAO.findAllTrains();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        Iterator<Train> iterator = trainList.iterator();
        while (iterator.hasNext()) {
            Train train = iterator.next();
            if (train.getActiveDaysList().stream().noneMatch(activeDays -> activeDays.getDay().equals(dayOfWeek))) {
                iterator.remove(); // Safe removal
            }
        }
        return trainList;
    }

    public List<TrainDeatils> getListOfTrainBySrcDstAndDate(String src, String dst, LocalDate date) {
        List<TrainDeatils> trainDeatilsList = getListOfTrainBySrcDst(src, dst);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        Iterator<TrainDeatils> iterator = trainDeatilsList.iterator();
        while (iterator.hasNext()) {
            TrainDeatils trainDeatils = iterator.next();
            if (trainDeatils.getTrain().getActiveDaysList().stream().noneMatch(activeDays -> activeDays.getDay().equals(dayOfWeek))) {
                iterator.remove(); // Safe removal
            }
        }
        return trainDeatilsList;
    }

    public Train addStops(Long trainNo, List<StopDetails> stopDetails) {
        List<Stops> stopsList = new ArrayList<>();
        for (StopDetails stopDetail : stopDetails) {
            Stops stops = new Stops();
            stops.setStation(stationDAO.getStation(stopDetail.getStationId()));
            stops.setStopNo(stopDetail.getStopNo());
            stops.setArrivalTime(stopDetail.getArrivalTime());
            stops.setDepartureTime(stopDetail.getDepartureTime());
            stopsList.add(stops);
        }
        Train train=trainDAO.findTrainByTrainNo(trainNo);
        return trainDAO.addTrainStops(train,stopsList);
    }

    public Train updateStops(Long trainNo, int stopNo, StopDetails stopDetails) {
        Train train = trainDAO.findTrainByTrainNo(trainNo);
        Stops stops = train.getStopsList().get(stopNo - 1);
        stops.setStation(stationDAO.getStation(stopDetails.getStationId()));
        stops.setArrivalTime(stopDetails.getArrivalTime());
        stops.setDepartureTime(stopDetails.getDepartureTime());
        return trainDAO.addTrainStops(train, train.getStopsList());
    }
}
