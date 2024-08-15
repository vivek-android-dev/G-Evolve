package com.saveetha.g_evolve.responses;

public class LoginResponse {
    private String error,status,message;
    public Data data;

    public Data getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Data{

        public String user_id;
        public String name;
        public String email;
        public String user_type;
        public String image;
        public String created_at;
        public String updated_at;



        public String recycler_id;
        public String company_name;
        public String capacity;
        public String address;
        public String contact;
        public String open_time;
        public String close_time;
        public String latitude;
        public String longitude;
        public String otp;

        public String getRecycler_id() {
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

        public String getOtp() {
            return otp;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getUser_type() {
            return user_type;
        }

        public String getImage() {
            return image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
