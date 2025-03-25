package com.RailSwift.Devlopment.Controller;

import com.RailSwift.Devlopment.DTO.TrainDeatils;
import com.RailSwift.Devlopment.DTO.TrainList;
import com.RailSwift.Devlopment.Entities.Stops;
import com.RailSwift.Devlopment.Entities.Train;
import com.RailSwift.Devlopment.Entities.TrainType;
import com.RailSwift.Devlopment.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/RailSwift/Train")
public class TrainController {
    TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostMapping("/AddTrain")
    public Train addTrain(@RequestBody Train train) {
        train.setStopsList(null);
        trainService.addTrain(train);
        return trainService.findTrainByTrainNo(train.getTrainNo());
    }

    @PostMapping("/AddMultipleTrain")
    public List<Train> addMultipleTrain(@RequestBody List<Train> trains) {
        List<Train> trainList = new ArrayList<>();
        for (Train train : trains) {
            train.setStopsList(null);
            trainService.addTrain(train);
            trainList.add(trainService.findTrainByTrainNo(train.getTrainNo()));
        }
        return trainList;
    }

    @GetMapping("/TrainDetails/{trainNo}")
    public Train findTrainByTrainNo(@PathVariable Long trainNo) {
        return trainService.findTrainByTrainNo(trainNo);
    }

    @GetMapping("/TrainDetails/Trains")
    public List<TrainList> getAllTrain() {
        List<Train> trains = trainService.findAllTrains();
        List<TrainList> trainLists = new ArrayList<>();
        for (Train train : trains) {
            int stops = train.getStopsList().size();
            TrainList trainList = new TrainList(train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getStopsList().get(0).getStation(), train.getStopsList().get(stops - 1).getStation());
            trainLists.add(trainList);
        }
        return trainLists;
    }

    @PutMapping("/UpdateTrainName/{trainNo}")
    public Train updateTrainName(@PathVariable Long trainNo, @RequestParam String trainName) {
        trainService.updateTrainName(trainNo, trainName);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @PutMapping("/UpdateTrainType/{trainNo}")
    public Train updateTrainType(@PathVariable Long trainNo, @RequestParam TrainType trainType) {
        trainService.updateTrainType(trainNo, trainType);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @PutMapping("/AddStops/{trainNo}")
    public Train updateTrainStops(@PathVariable Long trainNo, @RequestParam List<Stops> stops) {
        trainService.updateTrainStops(trainNo, stops);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @PutMapping("/AddTrainStop/{trainNo}")
    public Train addTrainStop(@PathVariable Long trainNo, @RequestParam Stops stop, @RequestParam int stopNo) {
        trainService.addTrainStop(trainNo, stop, stopNo);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @PutMapping("/DeleteTrainStop/{trainNo}")
    public Train deleteTrainStop(@PathVariable Long trainNo, @RequestParam int stopNo) {
        trainService.deleteTrainStop(trainNo, stopNo);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @PutMapping("/UpdateTrainStop/{trainNo}")
    public Train updateTrainStop(@PathVariable Long trainNo, @RequestParam Stops stop, @RequestParam int stopNo) {
        trainService.updateTrainStop(trainNo, stop, stopNo);
        return trainService.findTrainByTrainNo(trainNo);
    }

    @GetMapping("/TrainDetails/TrainsBySrcDst")
    public List<TrainDeatils> getListOfTrainBySrcDst(@RequestParam String src, @RequestParam String dst) {
        List<TrainDeatils> trainDeatilsList = trainService.getListOfTrainBySrcDst(src, dst);
        return trainDeatilsList;
    }

    @GetMapping("TrainDetails/TrainListOnDate")
    public List<Train> getTrainListOnDate(@RequestParam LocalDate date) {
//        LocalDate dt = LocalDate.parse("2018-01-32");
        return trainService.getListOfTrainOnDate(date);
    }

    @GetMapping("TrainDetails/TrainSrcDestOnDate")
    public List<TrainDeatils> getTrainSrcDestOnDate(@RequestParam String src, @RequestParam String dst, @RequestParam LocalDate date) {
        return trainService.getListOfTrainBySrcDstAndDate(src, dst, date);
    }

    @DeleteMapping("/DeleteTrain/{trainNo}")
    public String deleteTrain(@PathVariable Long trainNo) {
        trainService.deleteTrain(trainNo);
        return "Train with trainNo: " + trainNo + " deleted successfully";
    }

}
