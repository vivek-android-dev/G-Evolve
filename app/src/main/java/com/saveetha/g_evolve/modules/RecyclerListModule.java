package com.saveetha.g_evolve.modules;

public class RecyclerListModule  {
    private String recycler_id,companyName, email,contact, address, time, location;

    public RecyclerListModule(String recycler_id,String companyName, String email,String contact, String address, String time, String location) {

        this.recycler_id = recycler_id;
        this.companyName = companyName;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.time = time;
        this.location = location;

    }

    public String getRecycler_id() {
        return recycler_id;
    }

    public String getCompanyName() {
        return companyName;
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
}

