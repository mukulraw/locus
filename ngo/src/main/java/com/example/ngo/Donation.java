package com.example.ngo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Donation extends Activity {

	private EditText name;
	private EditText email;
	private EditText mobile;
	private EditText amount;
	
	private DatabaseHandler mDatabaseHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_donation);
		
		inti();
	}

	private void inti() {
		// TODO Auto-generated method stub
		name = (EditText) findViewById(R.id.editText1);
        email = (EditText) findViewById(R.id.editText2);
        mobile = (EditText) findViewById(R.id.editText3);
        amount = (EditText) findViewById(R.id.editText4);
        	
        mDatabaseHandler = DatabaseHandler.getInstance(Donation.this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.donation, menu);
		return true;
	}


	public void onRegister(View v) {

    if (!hasPopulated())
        return;

    DonationModle donationModel = new DonationModle();

    try {
    	donationModel.setName(name.getText().toString());
    	donationModel.setEmail(email.getText().toString());
    	donationModel.setMobile(mobile.getText().toString());
    	donationModel.setAmount(amount.getText().toString());
        } catch (Exception e) {
        e.printStackTrace();
    }

    
    
    if(email.getText().toString().endsWith(".com")&& email.getText().toString().contains("@"))
    {
    	
    	mDatabaseHandler.donation(donationModel);

   
    AlertDialog.Builder builder = new AlertDialog.Builder(Donation.this);
    builder.setTitle("Attention")
            .setMessage("You have Donated successfully.")
            .setCancelable(false)
            .setNegativeButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    finish();
                }
            });
    AlertDialog alert = builder.create();
    alert.show();
    
    startActivity(new Intent(Donation.this,
    		Donation.class));
	finish();

    
    }
    else
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(Donation.this);
        builder.setTitle("Attention")
                .setMessage("Not valid Email")
                .setCancelable(false)
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        

		startActivity(new Intent(Donation.this,
				Donation.class));
		finish();
	
    }
    	

}

/**
 * This method will check user has enter
 * value or not in edit text field.
 * @return true if all field full else false.
 */
private boolean hasPopulated() {

    if (name.getText().length() == 0) {
    	name.setError("Required");
        return false;
    } else if (email.getText().length() == 0) {
    	email.setError("Required");
        return false;
    } else if (mobile.getText().length() == 0) {
    	mobile.setError("Required");
        return false;
    } else if (amount.getText().length() == 0) {
    	amount.setError("Required");
        return false;
    } 
    return true;
}

/**
 * On click of cancel button closing the activity.
 * @param view passing view.
 */
public void onCancel(View v) {
    finish();
}
}


