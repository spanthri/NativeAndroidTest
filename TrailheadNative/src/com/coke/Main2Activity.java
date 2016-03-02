package com.coke;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    /** Called when the user clicks the login button */
    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void unAuthenticatedFlow(View view) {
        Intent intent = new Intent(this, UnAuthenticatedActivity.class);
        startActivity(intent);


    }

}
