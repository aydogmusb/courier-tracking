package com.example.couriertracking.vo;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class CourierVo {

    private Long id;
    private Date date;
    private Double latitude;
    private Double longitude;
    private Optional<Map<Long, Date>> entranceTime;

    public CourierVo() {
    }

    public CourierVo(Date date, Double latitude, Double longitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Optional<Map<Long, Date>> getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(Optional<Map<Long, Date>> entranceTime) {
        this.entranceTime = entranceTime;
    }
}
