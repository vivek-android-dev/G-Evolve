package com.saveetha.g_evolve.modules;

public class RecyclerListModule  {
    private String recycler_id,companyName, capacity,email,contact, address, time, location,open_time,close_time, latitude, longitude;

    public RecyclerListModule(String recycler_id,String companyName, String capacity,String email,String contact, String address, String time, String location, String open_time, String close_time, String latitude, String longitude) {

        this.recycler_id = recycler_id;
        this.companyName = companyName;
        this.capacity = capacity;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.time = time;
        this.location = location;
        this.open_time = open_time;
        this.close_time = close_time;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getRecycler_id() {
        return recycler_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    public String getContact() {
        return contact;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getOpen_time() {
        return open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

