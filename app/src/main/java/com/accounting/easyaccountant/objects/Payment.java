package com.accounting.easyaccountant.objects;

/**
 * Created by ahmed on 4/19/2018.
 */

public class Payment {
    public static final int  PAY = 0 ;
    public static final int  RECEIVE = 1 ;


    int id ;
    float ammount ;
    String desc ;
    String date ;
    int type ;
   public Payment(){

   }

    public Payment(int id, float ammount, String desc, String date, int type) {
        this.id = id;
        this.ammount = ammount;
        this.desc = desc;
        this.date = date;
        this.type = type;
    }

    public static int getPAY() {
        return PAY;
    }

    public static int getRECEIVE() {
        return RECEIVE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
