package com.app.locus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Dheeraj on 2/16/2016.
 */
public class Flipper extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper);





    }


    public void signin(View v)
    {

        Intent i = new Intent(this , SplashActivity.class);
        startActivity(i);


    }

    public void signup(View v)
    {
        Intent i = new Intent(this , Register.class);
        startActivity(i);


    }

    }
