package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    @NotNull
    private String restaurantId;

    @NotNull
    private String name;

    @NotNull
    private String city;

    private String imageUrl;

    private Double latitude;

    private Double longitude;

    @NotNull
    private String opensAt;

    @NotNull
    private String closesAt;

    private List<String> attributes = new ArrayList<>();

    // Getters and setters for all fields

    public void addAttribute(String attribute) {
        this.attributes.add(attribute);
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
}

