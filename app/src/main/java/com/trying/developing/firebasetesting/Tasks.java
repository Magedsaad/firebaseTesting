package com.trying.developing.firebasetesting;

/**
 * Created by developing on 4/3/2018.
 */

public class Tasks {

    private String id;
    private String memberEmail;
    private String taskName;
    private String taskDsc;
    private String taskDeadline;

    public Tasks() {}

    public Tasks(String id, String memberEmail, String taskName, String taskDsc, String taskDeadline) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.taskName = taskName;
        this.taskDsc = taskDsc;
        this.taskDeadline = taskDeadline;
    }

    public String getMemberEmail() {return memberEmail;}

    public String getTaskName() {return taskName;}

    public String getTaskDsc() {return taskDsc;}

    public String getTaskDeadline() {return taskDeadline;}

    public String getId() {
        return id;
    }
}
