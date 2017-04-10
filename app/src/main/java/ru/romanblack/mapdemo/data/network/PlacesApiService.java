package ru.romanblack.mapdemo.data.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.romanblack.mapdemo.data.network.response.PlaceDetailsResponse;
import ru.romanblack.mapdemo.data.network.response.PlacesSearchResponse;
import rx.Observable;

public interface PlacesApiService {

    @GET("nearbysearch/json")
    Observable<PlacesSearchResponse> nearbySearch(
            @Query("key") String key,
            @Query("location") String location,
            @Query("radius") int radius);

    @GET("details/json")
    Observable<PlaceDetailsResponse> placeDetails(
            @Query("key") String key,
            @Query("placeid") String placeId);

}
