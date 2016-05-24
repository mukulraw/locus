package com.app.locus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Dheeraj on 2/16/2016.
 */
public class Flipper extends Activity implements View.OnClickListener{

     private  TextView tvlogin;
     private  TextView tvregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper);

        tvlogin=(TextView) findViewById(R.id.login_txt);
        tvregister =(TextView) findViewById(R.id.register_txt);

        tvlogin.setOnClickListener(this);
        tvregister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

            switch (v.getId()){

            case R.id.login_txt:
                startActivity(new Intent(Flipper.this,
                        SplashActivity.class));
                break;

            case R.id.register_txt:
                startActivity(new Intent(Flipper.this,
                        Register.class));

                break;



        }



        }
    }
