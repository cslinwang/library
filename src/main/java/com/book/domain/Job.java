package com.book.domain;

public class Job {

    private int jobId;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getPassword() {
        return password;
    }

    public int getJobId() {
        return jobId;
    }
}
