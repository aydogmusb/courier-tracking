package com.example.couriertracking.service;

import com.example.couriertracking.converter.InfoAddFormToCourierVoConverter;
import com.example.couriertracking.vo.CourierVo;
import com.example.couriertracking.entity.Store;
import com.example.couriertracking.form.InfoAddForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CourierLocationCalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CourierLocationCalculatorService.class);

    private static final Map<Long, CourierVo> courierMap = new HashMap<>();

    private final StoreJsonMapperService storeJsonMapperService;
    private final InfoAddFormToCourierVoConverter infoAddFormToCourierVoConverter;

    public CourierLocationCalculatorService(StoreJsonMapperService storeJsonMapperService,
                                            InfoAddFormToCourierVoConverter infoAddFormToCourierVoConverter) {
        this.storeJsonMapperService = storeJsonMapperService;
        this.infoAddFormToCourierVoConverter = infoAddFormToCourierVoConverter;
    }

    public void checkCoordinates(InfoAddForm form) throws IOException {
        List<Store> stores = storeJsonMapperService.mapToStore();
        CourierVo courier = infoAddFormToCourierVoConverter.convertToVo(form);

        stores.forEach(store ->{
            Double latitude = store.getLatitude();
            Double longitude = store.getLongitude();

            double distance = Math.sqrt((longitude - (courier.getLongitude()) * (longitude - (courier.getLongitude()))) + ((latitude - (courier.getLatitude())) * (latitude - (courier.getLatitude()))));

            if(distance < 100 && controlCourierEntranceTime(courier, store.getId())){
                updateCourierEntranceTime(courier, store.getId());
                courierMap.put(form.getCourierId(), courier);
                logger.info("Courier {} enter {} store at {}", courier.getId(), store.getName(), form.getTime());
            }});
    }

    private boolean controlCourierEntranceTime(CourierVo courier, Long storeId) {
        if(courier.getEntranceTime().isPresent() && courier.getEntranceTime().get().containsKey(storeId)){
                return courier.getEntranceTime().get().get(storeId).after(addCourierTime(courier.getDate()));
        }
        return false;
    }

    private void updateCourierEntranceTime(CourierVo courier, Long storeId){
        Optional<Map<Long, Date>> entranceTime = courier.getEntranceTime();
        entranceTime.get().put(storeId, courier.getDate());
        courier.setEntranceTime(entranceTime);
    }

    private Date addCourierTime(Date courierTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(courierTime);
        c.add(Calendar.MINUTE, 1);
        return c.getTime();
    }
}
