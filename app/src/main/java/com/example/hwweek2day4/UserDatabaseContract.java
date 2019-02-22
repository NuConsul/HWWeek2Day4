package com.example.hwweek2day4;

import android.annotation.SuppressLint;
import android.util.Log;

public class UserDatabaseContract {

    //Database name and default version
    public static final String DATABASE_NAME = "user_db" ;
    public static final int DATABASE_VERSION = 1 ;

    //Database table name
    public static final String TABLE_NAME = "User" ;


    //Columns in database names
    public static final String COLUMN_NAME = "name" ;
    public static final String COLUMN_ADDRESS = "address" ;
    public static final String COLUMN_CITY = "city" ;
    public static final String COLUMN_STATE = "state" ;
    public static final String COLUMN_ZIPCODE = "zipcode" ;
    public static final String COLUMN_PHONENUMBER = "phonenumber" ;
    public static final String COLUMN_EMAILADDRESS = "emailaddress" ;
    public static final String COLUMN_ID = "id" ;
    //
    // Create the create table query
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder() ;

        queryBuilder.append("CREATE TABLE ") ;
        queryBuilder.append(TABLE_NAME) ;
        queryBuilder.append(" ( ") ;
        queryBuilder.append(COLUMN_ID) ;
        queryBuilder.append(" ") ;
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY KEY, ") ;
        queryBuilder.append(COLUMN_NAME) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_ADDRESS) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_CITY) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_STATE) ;
        queryBuilder.append(" TEXT, ") ;
        queryBuilder.append(COLUMN_ZIPCODE) ;
        queryBuilder.append(" TEXT ) ") ;

        /*
        COLUMN_PHONENUMBER ;
        COLUMN_EMAILADDRESS;
        TABLE_NAME
         */

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString()) ;


        return queryBuilder.toString() ;
    }



    public static String getAllCarsQuery() {
        return "SELECT * FROM " + TABLE_NAME ;
    }


    @SuppressLint("DefaultLocale")
    public static String getOneCarById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id) ;
        //return "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " ;
    }

}