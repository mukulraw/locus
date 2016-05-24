package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Youraddress extends Activity {

	private EditText etAddress;
    private EditText etMobileNumber;
    private EditText etEmailAddress;
    
    private DatabaseHandler mDatabaseHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youraddress);
		init();
	}

	private void init() {
        etAddress = (EditText) findViewById(R.id.editText3);
        etMobileNumber = (EditText) findViewById(R.id.editText4);
        etEmailAddress = (EditText) findViewById(R.id.editText5);
       
        mDatabaseHandler = DatabaseHandler.getInstance(Youraddress.this);
    }

	 public void onRegister(View view) {

	        if (!hasPopulated())
	            return;

	        UserModel userModel = new UserModel();

	        try {
	            userModel.setAddress(etAddress.getText().toString());
	            userModel.setMobileNumber(etMobileNumber.getText().toString());
	            userModel.setEmailAddress(etEmailAddress.getText().toString());
	              } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	        mDatabaseHandler.addVolunteer(userModel);

	        AlertDialog.Builder builder = new AlertDialog.Builder(Youraddress.this);
	        builder.setTitle("Attention")
	                .setMessage("You have Submit successfully.")
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

	    }

	 
	 private boolean hasPopulated() {

	        if (etAddress.getText().length() == 0) {
	            etAddress.setError("Required");
	            return false;
	        } else if (etMobileNumber.getText().length() == 0) {
	            etMobileNumber.setError("Required");
	            return false;
	        } else if (etEmailAddress.getText().length() == 0) {
	            etEmailAddress.setError("Required");
	            return false;
	        } 

	        return true;
	    }

	 public void onCancel(View view) {
	        finish();
	    }

/*	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.youraddress, menu);
		return true;
	}
*/
}
