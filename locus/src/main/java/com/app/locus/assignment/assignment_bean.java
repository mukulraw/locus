package com.app.locus.assignment;

import android.graphics.Bitmap;

/**
 * Created by hi on 25-05-2016.
 */
public class assignment_bean {

    String name , email , phone , country , subject , grade , reference , last_date , area_text , expected , location;
    Bitmap camera , browse;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public String getArea_text() {
        return area_text;
    }

    public void setArea_text(String area_text) {
        this.area_text = area_text;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public void setCamera(Bitmap camera) {
        this.camera = camera;
    }

    public Bitmap getCamera() {
        return camera;
    }

    public void setBrowse(Bitmap browse) {
        this.browse = browse;
    }

    public Bitmap getBrowse() {
        return browse;
    }


}

