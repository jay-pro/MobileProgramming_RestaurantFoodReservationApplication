package com.example.restaurantfoodreservationapplication;

public class UserHelperClass {
    String name, email, password, phone, position;

    public UserHelperClass(){

    }

    public UserHelperClass(String name, String email, String password, String phone, String position){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.position = position;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }
}
