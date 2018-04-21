package com.accounting.easyaccountant.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 4/20/2018.
 */

public class Bill {
    int id ;
    String ContactsID ;
    String date ;
    List<Integer> itemsIDs ;
    List<Integer> paymentsIDs ;
    float subTotal ;
    float totalDiscount ;
    float total ;
    float dueAmmount ;

    public Bill(int id, String contactsID, String date, List<Integer> itemsIDs, List<Integer> paymentsIDs, float subTotal, float totalDiscount, float total, float dueAmmount) {
        this.id = id;
        ContactsID = contactsID;
        this.date = date;
        this.itemsIDs = itemsIDs;
        this.paymentsIDs = paymentsIDs;
        this.subTotal = subTotal;
        this.totalDiscount = totalDiscount;
        this.total = total;
        this.dueAmmount = dueAmmount;
    }
    public Bill(int id, String contactsID, String date, float subTotal, float totalDiscount, float total, float dueAmmount) {
        this.id = id;
        ContactsID = contactsID;
        this.date = date;
        this.subTotal = subTotal;
        this.totalDiscount = totalDiscount;
        this.total = total;
        this.dueAmmount = dueAmmount;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactsID() {
        return ContactsID;
    }

    public void setContactsID(String contactsID) {
        ContactsID = contactsID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemsIDs() {
        String array = null;
        if(itemsIDs == null )
            return  null ;
        for (int a :itemsIDs) {
            array = array + a + " " ;
        }
        return array;

    }

    public void setItemsIDs(List<Integer> itemsIDs) {
        this.itemsIDs = itemsIDs;
    }
    public void setItemsIDs(String itemsIDs) {
       if(itemsIDs == null)
           return;
        String [] array = itemsIDs.split(" ");
        List<Integer>  list = new ArrayList<>();
        for (String s : array  ) {
            list.add(Integer.parseInt(s));
        }
        this.itemsIDs = list;
    }


    public String getPaymentsIDs() {
        String array = null;
        if(paymentsIDs == null )
            return  null ;
        for (int a :paymentsIDs) {
            array = array + a + " " ;
        }
        return array;
    }

    public void setPaymentsIDs(List<Integer> paymentsIDs) {

        this.paymentsIDs = paymentsIDs;
    }
    public void setPaymentsIDs(String paymentsIDs) {
        if(paymentsIDs == null)
            return;
        String [] array = paymentsIDs.split(" ");
        List<Integer>  list = new ArrayList<>();
        for (String s : array  ) {
            list.add(Integer.parseInt(s));
        }

        this.paymentsIDs = list;
    }


    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getDueAmmount() {
        return dueAmmount;
    }

    public void setDueAmmount(float dueAmmount) {
        this.dueAmmount = dueAmmount;
    }
}
