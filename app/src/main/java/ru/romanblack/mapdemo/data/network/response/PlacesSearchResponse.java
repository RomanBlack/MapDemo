package ru.romanblack.mapdemo.data.network.response;

import java.util.List;

import ru.romanblack.mapdemo.data.entity.Place;

public class PlacesSearchResponse {

    private String status;

    private List<Place> results;

    public String getStatus() {
        return status;
    }

    public List<Place> getResults() {
        return results;
    }
}
