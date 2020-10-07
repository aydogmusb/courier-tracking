package com.example.couriertracking.service;

import com.example.couriertracking.form.InfoAddForm;
import com.example.couriertracking.vo.CoordinatesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CourierInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CourierInfoService.class);
    private static final Map<Long, CoordinatesVo> courierCoordinates = new HashMap<>();
    private static final Double DEFAULT_TOTAL_DISTANCE = 0.0;

    public void addCourierInfo(InfoAddForm infoAddForn){
        Double latitude = infoAddForn.getLatitude();
        Double longitude = infoAddForn.getLongitude();

        if(courierCoordinates.containsKey(infoAddForn.getCourierId())) {
            CoordinatesVo coordinatesVo = courierCoordinates.get(infoAddForn.getCourierId());
            Double voLatitude = coordinatesVo.getLatitude();
            Double voLongitude = coordinatesVo.getLongitude();

            double distance = Math.sqrt((longitude - voLongitude * (longitude - voLongitude)) + ((latitude - voLatitude)) * (latitude - voLatitude));
            double totalDistance = Double.sum(distance, coordinatesVo.getTotalDistance());

            CoordinatesVo coordinatesVoNew = new CoordinatesVo.Builder()
            .latitude(infoAddForn.getLatitude())
            .longitude(infoAddForn.getLongitude())
            .totalDistance(totalDistance).build();

            courierCoordinates.put(infoAddForn.getCourierId(), coordinatesVoNew);
            logger.info("Courier total distance is {}", getTotalTravelDistance(infoAddForn.getCourierId()));
        }
        else{
            CoordinatesVo coordinatesVo = new CoordinatesVo.Builder()
                    .latitude(infoAddForn.getLatitude())
                    .longitude(infoAddForn.getLongitude())
                    .totalDistance(DEFAULT_TOTAL_DISTANCE).build();
            courierCoordinates.put(infoAddForn.getCourierId(), coordinatesVo);
        }
    }

    private double getTotalTravelDistance(Long courierId){
        if(courierCoordinates.containsKey(courierId)) {
            CoordinatesVo coordinatesVo = courierCoordinates.get(courierId);
            return coordinatesVo.getTotalDistance();
        }
        else {
            return DEFAULT_TOTAL_DISTANCE;
        }
    }
}
