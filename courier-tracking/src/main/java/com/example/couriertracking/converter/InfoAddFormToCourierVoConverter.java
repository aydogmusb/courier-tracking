package com.example.couriertracking.converter;

import com.example.couriertracking.form.InfoAddForm;
import com.example.couriertracking.vo.CourierVo;
import org.springframework.stereotype.Component;

@Component
public class InfoAddFormToCourierVoConverter {

    public CourierVo convertToVo(InfoAddForm form){
        CourierVo courierVo = new CourierVo();
        courierVo.setId(form.getCourierId());
        courierVo.setDate(form.getTime());
        courierVo.setLatitude(form.getLatitude());
        courierVo.setLongitude(form.getLongitude());
        return courierVo;
    }
}
