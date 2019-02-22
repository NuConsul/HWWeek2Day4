package com.example.hwweek2day4;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent ;
import android.view.View ;
import android.widget.EditText ;



public class DataEntryActivity extends Activity {

    //Constants
    public static final String KEY_User_RESULT = "User_result" ;
    public static final int RESULT_CODE = 580 ;

    EditText etUserMake, etUserModel, etUserYear, etUserColor, etUserTitle ;
    Intent sentIntent  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry) ;
        sentIntent = getIntent() ; //gets intent that started the activity
        bindViews() ;
    }

    public void bindViews() {
        etUserMake = (EditText) findViewById(R.id.etUserMake)  ;
        etUserModel = (EditText) findViewById(R.id.etUserModel) ;
        etUserYear = (EditText) findViewById(R.id.etUserYear) ;
        etUserColor = (EditText) findViewById(R.id.etUserColor) ;
        etUserTitle = (EditText) findViewById(R.id.etUserTitleStatus) ;
    }

    //Create User Object
    //@return User The new User object
    //
    public User generateUserObjectFromInput() {
        User returnUser = new User() ;  //the User we will return from method
        //Set-up User object
        returnUser.setUserMake(etUserMake.getText() != null ? etUserMake.getText().toString() : "") ;
        returnUser.setUserModel(etUserModel.getText() != null ? etUserModel.getText().toString() : "") ;
        returnUser.setUserYear(etUserYear.getText() != null ? etUserYear.getText().toString() : "") ;
        returnUser.setUserColor(etUserColor.getText() != null ? etUserColor.getText().toString() : "") ;
        returnUser.setUserTitleStatus(etUserTitle.getText() != null ? etUserTitle.getText().toString() : ""); ;

        return returnUser ;


    }

    public void onClickDataEntryActivity(View view) {
        User UserResult = generateUserObjectFromInput() ;
        Bundle bundleOfTheUserResult = new Bundle() ;
        bundleOfTheUserResult.putParcelable(KEY_User_RESULT, UserResult) ; //put User object in bundle
        sentIntent.putExtras(bundleOfTheUserResult) ; //attach the result bundle to the intent
        setResult(RESULT_CODE, sentIntent) ; //send back bundle with result to activity which called it for result
        finish() ; //Make sure the activity is flagged to be destroyed

    }
}


