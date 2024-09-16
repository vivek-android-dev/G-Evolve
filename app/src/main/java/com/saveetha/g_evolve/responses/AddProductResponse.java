package com.saveetha.g_evolve.responses;

import java.util.ArrayList;

public class AddProductResponse {

    String status,message;

    ArrayList<Products> Products;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    public ArrayList<Products> getProduct() {
        return Products;
    }


    public static class Products {


        String product_id,brand,model,price,date,time,location,phone,recycler,status,user_id,created_at,updated_at,user_name,user_email,category;

        public String getCategory() {
            return category;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getUser_email() {
            return user_email;
        }

        public String getProduct_id() {
            return product_id;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public String getPrice() {
            return price;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getLocation() {
            return location;
        }

        public String getPhone() {
            return phone;
        }

        public String getRecycler() {
            return recycler;
        }

        public String getStatus() {
            return status;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
