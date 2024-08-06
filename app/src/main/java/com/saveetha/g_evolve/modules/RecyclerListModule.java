package com.saveetha.g_evolve.modules;

public class RecyclerListModule  {
    private String companyName, email,contact, address, time, location;

    public RecyclerListModule(String companyName, String email,String contact, String address, String time, String location) {
        this.companyName = companyName;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.time = time;
        this.location = location;

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

