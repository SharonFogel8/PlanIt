package com.sharon.planitall.Objects;

import java.util.ArrayList;

public class TDL {
    private ArrayList<String> task;

    public TDL() {
        task=new ArrayList<>();
    }

    public ArrayList<String> getTask() {
        return task;
    }

    public TDL setTask(ArrayList<String> task) {
        this.task = task;
        return this;
    }
    public void addTask(String newTask){
        task.add(newTask);
    }
    public void deleteTask(int index){
        task.remove(index);
    }
}
