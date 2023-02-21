package com.sharon.planitall.Objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Events {
    private String name;
    private String my_uid;
    private HashMap<String,String> guestMap= new HashMap<>();
    private ArrayList<TDL> myTasks=null;
    private ArrayList<Schedule> schedules=null;
    private ArrayList<String> managers= new ArrayList<>();
    private int img=0;
    private float budget;
    private float leftOverMoney;
    private ArrayList<BudgetItem> shopping= new ArrayList<>();
    private eEventType eEventType;
    private int year;
    private int month;
    private int day;
    private int numOfInvited;
    private String location;

    public String getLocation() {
        return location;
    }

    public Events setLocation(String location) {
        this.location = location;
        return this;
    }
    public void newSchduleList(){
        schedules= new ArrayList<>();
    }
    public void newTasksList(){
        myTasks= new ArrayList<>();
    }

    public eEventType geteEventType() {
        return eEventType;
    }

    public Events seteEventType(eEventType eEventType) {
        this.eEventType = eEventType;
        return this;
    }

    public Events setLeftOverMoney(float leftOverMoney) {
        this.leftOverMoney = leftOverMoney;
        return this;
    }
    public Events setDate(int year,int month,int day){
        this.day=day;
        this.year=year;
        this.month=month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Events setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Events setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public Events setDay(int day) {
        this.day = day;
        return this;
    }


    public String getMy_uid() {
        return my_uid;
    }

    public Events setMy_uid(String my_uid) {
        this.my_uid = my_uid;
        return this;
    }

    public Events() {
    }


    public float getLeftOverMoney() {
        return leftOverMoney;
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



    public void setImg(int img){
        this.img=img;
    }
    public int getImg(){
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

    public int getNumOfInvited() {
        return numOfInvited;
    }

    public Events setNumOfInvited(int numOfInvited) {
        this.numOfInvited = numOfInvited;
        return this;
    }

    public ArrayList<BudgetItem> getShopping() {
        return shopping;
    }

    public Events setShopping(ArrayList<BudgetItem> shopping) {
        this.shopping = shopping;
        return this;
    }
    public Events addShop(BudgetItem budgetItem){
        leftOverMoney-=budgetItem.getCost();
        shopping.add(budgetItem);
        return this;
    }
    public Events removeShop(BudgetItem budgetItem){
        leftOverMoney+=budgetItem.getCost();
        shopping.remove(budgetItem);
        return this;
    }
    public Events updateBudget(){
        leftOverMoney= budget;
        for (int i = 0; i < shopping.size(); i++) {
            leftOverMoney-=shopping.get(i).getCost();
        }
        return this;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public Events setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
        return this;
    }
    public Events addSchedule(Schedule schedule) {
        schedules.add(schedule);
        sortSchedule();
        return this;
    }
    public void sortSchedule(){
        schedules.sort(compare_schedule);
    }
    public Events deleteSchedule(Schedule schedule) {
        schedules.remove(schedule);
        return this;
    }
    private Comparator<Schedule> compare_schedule = new Comparator<Schedule>() {
        @Override
        public int compare(Schedule schedule, Schedule t1) {
            return schedule.getStartHour().compareTo(t1.getStartHour());
        }
    };
}
