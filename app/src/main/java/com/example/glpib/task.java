package com.example.glpib;

public class task {
    public String Name;

    public String Id;
    public String Description;
    public String Status;
    public String Creator;
    public String Executor;
    public String Date;

    public task() {
    }

    public task(String id,String name, String description, String status, String creator, String executor, String date) {
        Id = id;
        Name = name;
        Description = description;
        Status = status;
        Creator = creator;
        Executor = executor;
        Date = date;
    }
}
