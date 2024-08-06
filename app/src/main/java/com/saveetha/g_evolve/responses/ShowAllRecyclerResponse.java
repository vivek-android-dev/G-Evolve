package com.saveetha.g_evolve.responses;

import java.util.ArrayList;
import java.util.Date;

public class ShowAllRecyclerResponse {
    public int status;
    public String message;
    public ArrayList<Recycler> recycler;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    public ArrayList<Recycler> getRecycler() {
        return recycler;
    }



    public class Recycler{
        public int recycler_id;
        public String company_name;
        public String capacity;
        public String address;
        public String email;
        public String contact;
        public String open_time;
        public String close_time;
        public String latitude;
        public String longitude;
        public Date created_at;
        public Date updated_at;

        public int getRecycler_id() {
            return recycler_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getCapacity() {
            return capacity;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getContact() {
            return contact;
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

        public Date getCreated_at() {
            return created_at;
        }

        public Date getUpdated_at() {
            return updated_at;
        }
    }
}
