package com.example.mama.logyus;

/**
 * Created by 16101219 on 12/4/2018.
 */

public class UEvent extends uEventId {

    String user_id;
    public String title;
    public String email;
    public String description;
    public String image;






    public UEvent()
    {

    }

    public UEvent(String title, String email, String description, String image,String user_id) {

        this.title = title;
        this.email = email;
        this.description = description;
        this.image = image;

        this.user_id=user_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setTime(String time) {
        this.email = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
