package ru.romanblack.mapdemo.data.network.response;

import ru.romanblack.mapdemo.data.entity.Place;

public class PlaceDetailsResponse {

    private String status;

    private Place result;

    public String getStatus() {
        return status;
    }

    public Place getResult() {
        return result;
    }
}
