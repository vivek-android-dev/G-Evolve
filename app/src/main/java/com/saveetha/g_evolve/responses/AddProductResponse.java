package com.saveetha.g_evolve.responses;

import java.util.ArrayList;

public class AddProductResponse {

    String status,message;

    ArrayList<Product> product;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    private class Product {

        String product_id,brand,model,price,date,time,location,phone,recycler,status,user_id,created_at,updated_at;

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
