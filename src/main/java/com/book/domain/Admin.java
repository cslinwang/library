package com.book.domain;

public class Admin {

    private int adminId;
    private String password;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public int getAdminId() {
        return adminId;
    }
}
