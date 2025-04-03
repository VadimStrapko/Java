package org.lab10;

public class MyData
{

    public static int ThisID;
    public static String ThisName;
    public static boolean isRegister = false;
    public int id;

    public String name;
    public int cost;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
