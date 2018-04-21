package com.accounting.easyaccountant.objects;

/**
 * Created by ahmed on 4/19/2018.
 */

public class Contact {
    int _id;
    String _name;
    String _sname;
    String _phone_number;
    String _email ;
    int _type ;
public final static int  CUSTOMER = 2 ;
    public final static int  SUPPLIER = 1 ;
    public final static int  USER = 0 ;

    public Contact(){   }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_sname() {
        return _sname;
    }

    public void set_sname(String _sname) {
        this._sname = _sname;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public int get_type() {
        return _type;
    }

    public void set_type(int _type) {
        this._type = _type;
    }

    public Contact(int _id, String _name, String _sname, String _phone_number, String _email, int _type) {
        this._id = _id;
        this._name = _name;
        this._sname = _sname;
        this._phone_number = _phone_number;
        this._email = _email;
        this._type = _type;
    }

    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
