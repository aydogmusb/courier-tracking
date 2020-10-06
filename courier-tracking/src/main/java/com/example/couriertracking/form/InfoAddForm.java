package com.example.couriertracking.form;

import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InfoAddForm {

    @NonNull
    private Double latitude;

    @NonNull
    private Double longitude;

    @NonNull
    private Long courierId;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date time;

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

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
