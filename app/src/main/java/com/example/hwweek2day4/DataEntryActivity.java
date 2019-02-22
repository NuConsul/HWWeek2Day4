package com.example.hwweek2day4;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent ;
import android.view.View ;
import android.widget.EditText ;



public class DataEntryActivity extends Activity {

    //Constants
    public static final String KEY_USER_RESULT = "User_result" ;
    public static final int RESULT_CODE = 580 ;


    EditText etUserName, etUserAddress, etUserCity, etUserState, etUserZipCode, etUserPhoneNumber, etUserEmailAddress ;
    Intent sentIntent  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry) ;
        sentIntent = getIntent() ; //gets intent that started the activity
        bindViews() ;
    }

    public void bindViews() {
        etUserName = (EditText) findViewById(R.id.etUserName)  ;
        etUserAddress = (EditText) findViewById(R.id.etUserAddress) ;
        etUserCity = (EditText) findViewById(R.id.etUserCity) ;
        etUserState = (EditText) findViewById(R.id.etUserState) ;
        etUserZipCode = (EditText) findViewById(R.id.etUserZipCode) ;
        etUserPhoneNumber = (EditText) findViewById(R.id.etUserPhoneNumber) ;
        etUserEmailAddress = (EditText) findViewById(R.id.etUserEmailAddress) ;
    }

    //Create User Object
    //@return User The new User object
    //
    public User generateUserObjectFromInput() {
        User returnUser = new User() ;  //the User we will return from method
        //Set-up User object
        returnUser.setName(etUserName.getText() != null ? etUserName.getText().toString() : "") ;
        returnUser.setAddress(etUserAddress.getText() != null ? etUserAddress.getText().toString() : "") ;
        returnUser.setCity(etUserCity.getText() != null ? etUserCity.getText().toString() : "") ;
        returnUser.setState(etUserState.getText() != null ? etUserState.getText().toString() : "") ;
        returnUser.setZipCode(etUserZipCode.getText() != null ? etUserZipCode.getText().toString() : "") ;
        returnUser.setPhoneNumber(etUserPhoneNumber.getText() != null ? etUserPhoneNumber.getText().toString() : " ") ;
        returnUser.setEmailAddress(etUserEmailAddress.getText() != null ? etUserEmailAddress.getText().toString() : " ") ;

        return returnUser ;


    }

    public void onClickDataEntryActivity(View view) {

        User UserResult = generateUserObjectFromInput() ;
        Bundle bundleOfTheUserResult = new Bundle() ;
        bundleOfTheUserResult.putParcelable(KEY_USER_RESULT, UserResult) ; //put User object in bundle
        sentIntent.putExtras(bundleOfTheUserResult) ; //attach the result bundle to the intent
        setResult(RESULT_CODE, sentIntent) ; //send back bundle with result to activity which called it for result
        finish() ; //Make sure the activity is flagged to be destroyed

    }
}


