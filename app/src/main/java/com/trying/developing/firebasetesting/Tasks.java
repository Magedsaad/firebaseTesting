package com.trying.developing.firebasetesting;

/**
 * Created by developing on 4/3/2018.
 */

public class Tasks {

    private String mMemberEmail;
    private String mTaskname;
    private String mTaskDsc;
    private String mTaskDeadline;


    public Tasks() {
    }

    public Tasks(String mMemberEmail, String mTaskname, String mTaskDsc, String mTaskDeadline) {
        this.mMemberEmail = mMemberEmail;
        this.mTaskname = mTaskname;
        this.mTaskDsc = mTaskDsc;
        this.mTaskDeadline = mTaskDeadline;
    }

    public String getmMemberEmail() {
        return mMemberEmail;
    }

    public void setmMemberEmail(String mMemberEmail) {
        this.mMemberEmail = mMemberEmail;
    }

    public String getmTaskname() {
        return mTaskname;
    }

    public void setmTaskname(String mTaskname) {
        this.mTaskname = mTaskname;
    }

    public String getmTaskDsc() {
        return mTaskDsc;
    }

    public void setmTaskDsc(String mTaskDsc) {
        this.mTaskDsc = mTaskDsc;
    }

    public String getmTaskDeadline() {
        return mTaskDeadline;
    }

    public void setmTaskDeadline(String mTaskDeadline) {
        this.mTaskDeadline = mTaskDeadline;
    }
}
