package com.example.hwweek2day4;


/*
Create an Activity where you enter a Users:
Name
Address
City
State
Zip
Phone Number
Email Address

Upon a button click, save the information to the database and save the name to shared Preferences
Log the list of current users in the DB in the logcat after you insert into the database
Have a textview display the user name currently saved in shared pref
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint ;
import android.content.Intent ;
import android.content.SharedPreferences ;
import android.content.Intent ;
import android.support.annotation.NonNull ;
import android.support.annotation.Nullable ;
import android.os.Bundle ;
import android.util.Log ;
import android.view.View ;
import android.widget.TextView ;
import java.util.ArrayList ;

import static com.example.hwweek2day4.DataEntryActivity.KEY_User_RESULT;

public class MainActivity extends AppCompatActivity {

    //Constant for request code
    public static final int REQUEST_CODE_FOR_MAIN = 426 ;
    public static final String KEY_SHARED_PREF = "shared_pref" ;
    public static final String KEY_LAST_ENTERED_MAKE = "last_make" ;
    public static final String KEY_LAST_ENTERED_MODEL = "last_model" ;

    //Two ways of starting an activity
    //startActivity which -- starts an Activity
    //or startActivityForResult -- which starts an activity for the purpose of a result to bring
    //back to the initial activity

    TextView tvUserMakeDisplay, tvUserModelDisplay, tvUserYearDisplay, tvUserColorDisplay, tvUserTitleStatusDisplay ;
    //Shared Preferences Object
    SharedPreferences sharedPreferences ;

    UserDatabaseHelper userDatabaseHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE) ;

        //instantiate the User Database
        userDatabaseHelper = new UserDatabaseHelper(this) ;

        bindViews() ;
    }

    public void bindViews() {
        tvUserMakeDisplay = (TextView) findViewById(R.id.tvUserMake) ;
        tvUserModelDisplay = (TextView) findViewById(R.id.tvUserModel) ;
        tvUserYearDisplay = (TextView) findViewById(R.id.tvUserYear) ;
        tvUserColorDisplay = (TextView) findViewById(R.id.tvUserColor) ;
        tvUserTitleStatusDisplay = (TextView) findViewById(R.id.tvUserTitleStatus) ;
    }


    //@param User User info to populate
    //@return void
    public void populateTextViews(@NonNull User UserInfoToPopulate) {
        tvUserMakeDisplay.setText(UserInfoToPopulate.getUserMake()) ;
        tvUserModelDisplay.setText(UserInfoToPopulate.getUserModel()) ;
        tvUserYearDisplay.setText(UserInfoToPopulate.getUserYear()) ;
        tvUserColorDisplay.setText(UserInfoToPopulate.getUserColor()) ;
        tvUserTitleStatusDisplay.setText(UserInfoToPopulate.getUserTitleStatus()) ;
    }




    //
    //Save to Shared Pref.
    //@param User User object which we will save info to shared pref. from
    //@return void
    //
    //@SuppressLint("ApplySharedPref")
    @SuppressLint("ApplySharedPref")
    public void saveMakeModelToSharedPref(@NonNull User User) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit() ;
        sharedPrefEditor.putString(KEY_LAST_ENTERED_MAKE, User.getUserMake()) ;
        sharedPrefEditor.putString(KEY_LAST_ENTERED_MODEL, User.getUserModel()) ;
        sharedPrefEditor.commit() ;
    }


    //
    //Log shared pref values and save new User
    // @param User New User to saved
    //
    public void saveAndLogUserInSharedPref(@NonNull User User) {

        //Get old values saved in pref
        String make = sharedPreferences.getString(KEY_LAST_ENTERED_MAKE, "NO VALUE ENTERED") ;
        String model = sharedPreferences.getString(KEY_LAST_ENTERED_MODEL, "NO VALUE ENTERED") ;

        //Log the old values
        Log.d("TAG", "saveAndLogUserInSharedPref: IN SHARED PREF: make = " + make + " | model = " + model) ;

        //Save new values to shared pref
        saveMakeModelToSharedPref(User) ;

        //get new values
        make = sharedPreferences.getString(KEY_LAST_ENTERED_MAKE, "NO VALUE ENTERED") ;
        model = sharedPreferences.getString(KEY_LAST_ENTERED_MODEL, "NO VALUE ENTERED") ;

        //log the new values
        Log.d("TAG", "saveAndLogUserInSharedPref: IN SHARED PREF: make = " + make + " | model = " + model) ;

    }


    //
    //Save User to database and print list of all Users currently in DB to log
    //
    public void saveUserToDBandSeeLog(@NonNull User User) {

        //Save User to database
        userDatabaseHelper.insertUserIntoDatabase(User) ;
        //get all current Users in database and log them
        ArrayList<User> UserList = userDatabaseHelper.getAllUsersFromDatabase() ;
        for(User currentUser : UserList) {
            Log.d("TAG", currentUser.toString()) ;
        }

    }






    //Create a explicit intent to start data entry
    //activity for result, and start the activity for result
    //@return void////<---- Java Doc convention

    public void onClickMainActivity(View view) {

        switch(view.getId()) {
            case R.id.btnStartForResult:
                Intent explicitIntent = new Intent(this, DataEntryActivity.class) ;
                startActivityForResult(explicitIntent, REQUEST_CODE_FOR_MAIN) ;
                break ;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data) ;


        //null check the intent sent back with result
        if(data != null) {
            Bundle resultBundle = data.getExtras() ;
            if(resultBundle != null) {
                User resultUser = resultBundle.getParcelable(KEY_USER_RESULT) ;

                if(resultUser != null) {
                    saveAndLogUserInSharedPref(resultUser) ; //save and log make model in shared pref
                    populateTextViews(resultUser) ; //populate the views
                    saveUserToDBandSeeLog(resultUser) ; //save User to db and print list of all Users in db
                }
            }
        }



    }
}





