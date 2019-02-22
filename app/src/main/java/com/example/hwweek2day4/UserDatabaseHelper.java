package com.example.hwweek2day4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper ;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/*
private String name ;
private String address ;
private String city ;
private String state ;
private int zipCode ;
private int phoneNumber ;
private String emailAddress; */



import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_ID ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_NAME ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_ADDRESS ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_CITY ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_STATE ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_ZIPCODE ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_PHONENUMBER ;
import static com.example.hwweek2day4.UserDatabaseContract.COLUMN_EMAILADDRESS ;
import static com.example.hwweek2day4.UserDatabaseContract.DATABASE_NAME ;
import static com.example.hwweek2day4.UserDatabaseContract.DATABASE_VERSION ;
import static com.example.hwweek2day4.UserDatabaseContract.TABLE_NAME ;

public class UserDatabaseHelper extends SQLiteOpenHelper {


    //Constructor for
    public UserDatabaseHelper(@android.support.annotation.Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }


    //Create the tables(will run only once per install)
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UserDatabaseContract.createQuery()) ;
    }


    //If version of database changes, make adjustments here
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database) ;
    }


    //Insert a User into the database
    public long insertUserIntoDatabase(@NonNull User User) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase() ;

        //Data holder/container/variable used for database key value pairs
        ContentValues contentValues = new ContentValues() ;



        //insert key value paris into the contentValues variable
        contentValues.put(COLUMN_NAME, User.getName()) ;
        contentValues.put(COLUMN_ADDRESS, User.getAddress()) ;
        contentValues.put(COLUMN_CITY, User.getCity()) ;
        contentValues.put(COLUMN_STATE, User.getState()) ;
        contentValues.put(COLUMN_ZIPCODE, User.getZipCode()) ;
        contentValues.put(COLUMN_PHONENUMBER, User.getPhoneNumber()) ;
        contentValues.put(COLUMN_EMAILADDRESS, User.getEmailAddress()) ;
        contentValues.put(COLUMN_ID, User.getId()) ;


        //insert the User into the table using contentValues
        return writeableDatabase.insert(TABLE_NAME, null, contentValues) ;

    }

    //Get All Users from Database and return an ArrayList
    public ArrayList<User> getAllUsersFromDatabase() {
        ArrayList<User> returnUserList = new ArrayList<>() ;
        SQLiteDatabase readableDatabase = this.getReadableDatabase() ;

        //Get results from query and hold in cursor (iterable object for database operations)
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getAllUsersQuery(), null) ;

        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)) ;
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)) ;
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)) ;
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY)) ;
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE)) ;
                String zipCode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE)) ;
                String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER)) ;
                String emailAddress = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)) ;



                //add to list
                returnUserList.add(new User(name, address, city, state, zipCode, phoneNumber, emailAddress, id)) ;


            } while(cursor.moveToNext()) ;
        }


        //

        cursor.close() ;
        //return the result in a list
        return returnUserList ;
    }


    //Get One entry from database
    public User getUserById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase() ;

        //User to return
        User returnUser = new User() ;

        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getOneUserById(id), null) ;
        if(cursor.moveToFirst()) {

            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)) ;
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME)) ;
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)) ;
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY)) ;
            String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE)) ;
            String zipCode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE)) ;
            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER)) ;
            String emailAddress = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)) ;

            //set return User
            returnUser = new User(name, address, city, state, zipCode, phoneNumber, emailAddress, idFromDB) ;
        }


        cursor.close() ;
        return returnUser ;
    }


}