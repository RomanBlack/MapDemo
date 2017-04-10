package ru.romanblack.mapdemo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Place implements Serializable {

    private String id;

    @SerializedName("place_id")
    private String placeId;

    private String name;

    private String icon;

    private String vicinity;

    @SerializedName("formatted_address")
    private String address;

    @SerializedName("international_phone_number")
    private String phone;

    private String website;

    private Geometry geometry;

    private List<Photo> photos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public double getLatitude() {
        if (geometry != null && geometry.location != null) {
            return geometry.location.lat;
        }

        return 0;
    }

    public double getLongitude() {
        if (geometry != null && geometry.location != null) {
            return geometry.location.lng;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Place) {
            Place p = (Place) obj;
            return placeId.equals(p.placeId);
        }

        return false;
    }

    public static class Geometry {
        Location location;
    }

    public static class Location {

        private double lat;
        private double lng;
    }

    public static class Photo {

        @SerializedName("photo_reference")
        private String reference;

        private int height;
        private int width;

        public String getReference() {
            return reference;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
