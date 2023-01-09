package com.sharon.planitall.Objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Events {
    private String name;
    private String UID;
    private HashMap<String,String> guestMap= new HashMap<>();
    private ArrayList<TDL> myTasks=new ArrayList<>();
    private Schedule Schedule;
    private ArrayList<String> managers;
    private String img;
    private float budget;
    private float leftOverMoney;
    private HashMap<String,Float> shopping= new HashMap<>();

    public Events setLeftOverMoney(float leftOverMoney) {
        this.leftOverMoney = leftOverMoney;
        return this;
    }

    public Events setShopping(HashMap<String, Float> shopping) {
        this.shopping = shopping;
        return this;
    }

    public String getUID() {
        return UID;
    }

    public Events setUID(String UID) {
        this.UID = UID;
        return this;
    }

    public Events() {
    }


    public float getLeftOverMoney() {
        return leftOverMoney;
    }

    public HashMap<String, Float> getShopping() {
        return shopping;
    }

    public String getName() {
        return name;
    }
    public void addManager(String UID){
        managers.add(UID);
    }
    public void addTask(TDL newTask){
        myTasks.add(newTask);
    }
    public void addGuest(String phoneNum, String name){
        guestMap.put(phoneNum,name);
    }
    public void addShop(String name,float price){
        shopping.put(name,price);
        leftOverMoney-=price;
    }
    public void deleteShop(String name,float price){
        leftOverMoney+=price;
        shopping.remove(name,price);
    }

    public void setImg(String img){
        this.img=img;
    }
    public String getImg(){
        return img;
    }
    public void deleteGuest(String phoneNum){
        guestMap.remove(phoneNum);
    }

    public Events setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<String, String> getGuestMap() {
        return guestMap;
    }

    public Events setGuestMap(HashMap<String, String> guestMap) {
        this.guestMap = guestMap;
        return this;
    }

    public ArrayList<TDL> getMyTasks() {
        return myTasks;
    }

    public Events setMyTasks(ArrayList<TDL> myTasks) {
        this.myTasks = myTasks;
        return this;
    }

    public Schedule getSchedule() {
        return Schedule;
    }

    public Events setSchedule(Schedule schedule) {
        Schedule = schedule;
        return this;
    }

    public ArrayList<String> getManagers() {
        return managers;
    }

    public Events setManagers(ArrayList<String> managers) {
        this.managers = managers;
        return this;
    }
    public float getBudget() {
        return budget;
    }

    public Events setBudget(float budget) {
        leftOverMoney=budget;
        this.budget = budget;
        return this;
    }

}
