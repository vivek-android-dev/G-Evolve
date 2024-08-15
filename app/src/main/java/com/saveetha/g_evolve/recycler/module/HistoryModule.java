package com.saveetha.g_evolve.recycler.module;

public class HistoryModule {

    String product_id,brand,model,price,date,time,location,phone,recycler,status,user_id,created_at,updated_at,user_name,user_email;

    public HistoryModule(String product_id, String brand, String model, String price, String date, String time, String location, String phone, String recycler, String status, String user_id, String created_at, String updated_at, String user_name, String user_email) {
        this.product_id = product_id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.date = date;
        this.time = time;
        this.location = location;
        this.phone = phone;
        this.recycler = recycler;
        this.status = status;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecycler() {
        return recycler;
    }

    public void setRecycler(String recycler) {
        this.recycler = recycler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
