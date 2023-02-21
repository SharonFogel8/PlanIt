package com.sharon.planitall.Objects;

public class BudgetItem {
    private String name;
    private float cost;
    private String type;

    public BudgetItem() {
    }

    public String getName() {
        return name;
    }

    public BudgetItem setName(String name) {
        this.name = name;
        return this;
    }

    public float getCost() {
        return cost;
    }

    public BudgetItem setCost(float cost) {
        this.cost = cost;
        return this;
    }

    public String getType() {
        return type;
    }

    public BudgetItem setType(String type) {
        this.type = type;
        return this;
    }
}
