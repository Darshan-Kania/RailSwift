package com.RailSwift.Devlopment.Controller;

import com.RailSwift.Devlopment.DTO.StopDetails;
import com.RailSwift.Devlopment.DTO.TrainDeatils;
import com.RailSwift.Devlopment.DTO.TrainList;
import com.RailSwift.Devlopment.Entities.Station;
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
            List<Stops> stopsList = train.getStopsList();

            if (stopsList == null || stopsList.isEmpty()) {
                // Handle empty stops list appropriately, maybe skip this train or add default values
                continue; // Skipping this train if there are no stops
            }

            int stops = stopsList.size();
            Station src = stopsList.get(0).getStation();
            Station dst = stopsList.get(stops - 1).getStation();
            TrainList trainList = new TrainList(
                    train.getTrainNo(),
                    train.getTrainName(),
                    train.getTrainType(),
                    src,
                    dst
            );

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


    @GetMapping("/TrainDetails/TrainsBySrcDst/{src}/{dst}")
    public List<TrainDeatils> getListOfTrainBySrcDst(@PathVariable String src, @PathVariable String dst) {
        List<TrainDeatils> trainDeatilsList = trainService.getListOfTrainBySrcDst(src, dst);
        return trainDeatilsList;
    }

    @GetMapping("/TrainDetails/TrainListOnDate")
    public List<Train> getTrainListOnDate(@RequestParam LocalDate date) {
//        LocalDate dt = LocalDate.parse("2018-01-32");
        return trainService.getListOfTrainOnDate(date);
    }

    @GetMapping("/TrainDetails/TrainsBySrcDstOnDate/{src}/{dst}/{date}")
    public List<TrainDeatils> getTrainSrcDestOnDate(@PathVariable String src, @PathVariable String dst, @PathVariable LocalDate date) {
        return trainService.getListOfTrainBySrcDstAndDate(src, dst, date);
    }

    @DeleteMapping("/DeleteTrain/{trainNo}")
    public String deleteTrain(@PathVariable Long trainNo) {
        trainService.deleteTrain(trainNo);
        return "Train with trainNo: " + trainNo + " deleted successfully";
    }

    //    Working
    @PutMapping("/AddTrainStops/{trainNo}")
    public Train addTrainStops(@PathVariable Long trainNo, @RequestBody List<StopDetails> stopDetails) {
        return trainService.addStops(trainNo, stopDetails);
    }
}
