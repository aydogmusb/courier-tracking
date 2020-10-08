package com.example.couriertracking.vo;

public class CoordinatesVo {

    private Double latitude;
    private Double longitude;
    private double totalDistance;

    public CoordinatesVo(Double latitude, Double longitude, double totalDistance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalDistance = totalDistance;
    }

    public static class Builder {

        private Double latitude;
        private Double longitude;
        private double totalDistance;

        public Builder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder totalDistance(double totalDistance) {
            this.totalDistance = totalDistance;
            return this;
        }

        public CoordinatesVo build() {
            return new CoordinatesVo(this);
        }
    }

    private CoordinatesVo(Builder builder) {
        latitude = builder.latitude;
        longitude = builder.longitude;
        totalDistance = builder.totalDistance;
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

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
