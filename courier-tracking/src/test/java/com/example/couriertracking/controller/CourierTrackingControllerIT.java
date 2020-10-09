package com.example.couriertracking.controller;

import com.example.couriertracking.form.InfoAddForm;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CourierTrackingControllerIT extends BaseIT {

    private static String URL = "/info";

    @Test
    public void should_check_courier_info() {
        InfoAddForm infoAddForm = new InfoAddForm();
        infoAddForm.setLatitude(41.0066851);
        infoAddForm.setLongitude(23.002531);
        infoAddForm.setCourierId(1L);
        infoAddForm.setTime(DateUtil.now());

        //given
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange(URL, HttpMethod.POST, new HttpEntity<>(infoAddForm, headers), String.class);

        assertThat(responseEntity).isNotNull();
    }
}