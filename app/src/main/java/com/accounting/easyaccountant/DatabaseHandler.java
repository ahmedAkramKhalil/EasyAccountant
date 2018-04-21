package com.accounting.easyaccountant;

/**
 * Created by ahmed on 4/19/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.accounting.easyaccountant.objects.Bill;
import com.accounting.easyaccountant.objects.Contact;
import com.accounting.easyaccountant.objects.Item;
import com.accounting.easyaccountant.objects.Payment;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_ITEMS = "items";
    private static final String TABLE_PAYMENT = "payment";
    private static final String TABLE_Bill = "bill";


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_TYPE = "type";
    private static final String KEY_S_NAME = "sname";
    private static final String KEY_EMAIL = "email";

private static   DatabaseHandler databaseHandler = null ;

    static Context context = null ;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
    public  static DatabaseHandler getInstance(){
        if (databaseHandler == null ){
            databaseHandler = new DatabaseHandler(MainActivity.getContext()) ;
        }
        return  databaseHandler ;
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_TYPE + " TEXT," + KEY_S_NAME + " TEXT,"+ KEY_EMAIL + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        String CREATE_ITEMS = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + "disc" + " TEXT," + "unit" + " TEXT,"+ "price"  + " TEXT,"+ "quantity" + " TEXT,"+ "discount" + " TEXT,"
                + "total" + " TEXT" + ")";
        String CREATE_PAYMENT = "CREATE TABLE " + TABLE_PAYMENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "ammount" + " TEXT," + "disc" + " TEXT," + "date" + " TEXT,"+ "type"  + " TEXT" + ")";
        String CREATE_Bill = "CREATE TABLE " + TABLE_Bill + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "customer" + " TEXT," + "date" + " TEXT," + "items" + " TEXT,"+ "payment"+ " TEXT,"+ "subtotal" + " TEXT,"+ "discount"+ " TEXT,"+ "total" + " TEXT,"+ "dueammount"+ " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_ITEMS);
        db.execSQL(CREATE_PAYMENT);
        db.execSQL(CREATE_Bill);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Bill);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
   public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); //
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //
        values.put(KEY_S_NAME, contact.get_sname()); //
        values.put(KEY_EMAIL, contact.get_email()); //
        values.put(KEY_TYPE, contact.get_type()); //

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
   public  void addBill(Bill bill) {
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_Bill = "CREATE TABLE " + TABLE_Bill + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "customer" + " TEXT," + "date" + " TEXT," + "items" + " TEXT,"+ "payment"+ " TEXT,"+ "subtotal" + " TEXT,"+ "discount"+ " TEXT,"+ "total" + " TEXT,"+ "dueammount"+ " TEXT" + ")";


        ContentValues values = new ContentValues();
        values.put("customer", bill.getContactsID()); //
        values.put("date", "" +bill.getDate()); //
        values.put("items", "" +bill.getItemsIDs()); //
        values.put("payment","" + bill.getPaymentsIDs()); //
        values.put("subtotal","" + bill.getSubTotal()); //
        values.put("discount", "" +bill.getTotalDiscount()); //
        values.put("total","" + bill.getTotal()); //
        values.put("dueammount", "" +bill.getDueAmmount()); //

        // Inserting Row
        db.insert(TABLE_Bill, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
  public   void addPayment(Payment payment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ammount","" + payment.getAmmount()); //
        values.put("disc", payment.getDesc()); //
        values.put("date", payment.getDate()); //
        values.put("type", "" +payment.getType()); //

        // Inserting Row
        db.insert(TABLE_PAYMENT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
  public  void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName()); //
        values.put("disc", "" +item.getDisc()); //
        values.put("unit", "" +item.getUnit()); //
        values.put("price", "" +item.getPrice()); //
        values.put("quantity", "" +item.getQuantity()); //
        values.put("discount", "" +item.getDiscount()); //
        values.put("total", "" +item.getTotal()); //

        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
//           contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
        // return contact
        return contact;
    }
    Payment getPayment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String CREATE_PAYMENT = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + "ammount" + " TEXT," + "disc" + " TEXT," + "date" + " TEXT,"+ "type"  + " TEXT" + ")";

        Cursor cursor = db.query(TABLE_PAYMENT, new String[] { KEY_ID, "ammount" , "disc","date","type" }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Payment payment = new Payment(Integer.parseInt(cursor.getString(0)),Float.parseFloat(cursor.getString(1)),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)));
//           contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
        // return contact
        return payment;
    }


    Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS, new String[] { KEY_ID, KEY_NAME, "disc" ,"unit", "price","quantity","discount", "total"}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Item item = new Item(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)));
//           contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
        // return contact
        return item;
    }

    // code to get all contacts in a list view
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                String CREATE_ITEMS = "CREATE TABLE " + TABLE_ITEMS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + "disc" + " TEXT," + "unit" + " TEXT,"+ "price"  + " TEXT,"+ "quantity" + " TEXT,"+ "discount" + " TEXT,"
                        + "total" + " TEXT" + ")";

                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setDisc(cursor.getString(2));
                item.setUnit(cursor.getString(3));
                item.setPrice(Float.parseFloat(cursor.getString(4)));
                item.setQuantity(Float.parseFloat(cursor.getString(5)));
                item.setDiscount(Float.parseFloat(cursor.getString(6)));
                item.setTotal(Float.parseFloat(cursor.getString(7)));
                list.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }
    public List<Item> getAllItems(List<Integer> listInt) {
        List<Item> list = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                String CREATE_ITEMS = "CREATE TABLE " + TABLE_ITEMS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + "disc" + " TEXT," + "unit" + " TEXT,"+ "price"  + " TEXT,"+ "quantity" + " TEXT,"+ "discount" + " TEXT,"
                        + "total" + " TEXT" + ")";
                 if(listInt.contains(Integer.parseInt(cursor.getString(0)))){
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setDisc(cursor.getString(2));
                item.setUnit(cursor.getString(3));
                item.setPrice(Float.parseFloat(cursor.getString(4)));
                item.setQuantity(Float.parseFloat(cursor.getString(5)));
                item.setDiscount(Float.parseFloat(cursor.getString(6)));
                item.setTotal(Float.parseFloat(cursor.getString(7)));
                list.add(item);
                 }
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }
    public List<Contact> getAllSuppliers() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst() &&Integer.parseInt(cursor.getString(2)) == 1 ) {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Contact> getAllCustomer() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst() &&Integer.parseInt(cursor.getString(2)) == 2 ) {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(3),cursor.getString(5),cursor.getString(4),Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<Bill>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Bill;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
//        if (cursor.moveToFirst() &&Integer.parseInt(cursor.getString(2)) == 2 ) {
            do {
//                Bill bill = new Bill(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Float.parseFloat(cursor.getString(5)),Float.parseFloat(cursor.getString(6)),Float.parseFloat(cursor.getString(7)),Float.parseFloat(cursor.getString(8)));


                String CREATE_Bill = "CREATE TABLE " + TABLE_Bill + "("
                        + KEY_ID + " INTEGER PRIMARY KEY," + "customer" + " TEXT," + "date" + " TEXT," + "items" + " TEXT,"+ "payment"+ " TEXT,"+ "subtotal" + " TEXT,"+ "discount"+ " TEXT,"+ "total" + " TEXT,"+ "dueammount"+ " TEXT" + ")";


                try {
                    Bill bill = new Bill();
                    bill.setContactsID(cursor.getString(1));
                    bill.setDate(cursor.getString(2));
                    bill.setTotal(Float.parseFloat(cursor.getString(7)));
                    list.add(bill);
                }catch (Exception e) {

                }
            } while (cursor.moveToNext());
//        }

        // return contact list
        return list;
    }


    // code to update the single contact
//    public int updateContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//        // updating row
//        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
