package com.example.ngo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login1 extends Activity {

	private EditText	etEmailAddress;
	private EditText	etPassword;

	private DatabaseHandler	mDatabaseHandler;
	AlertDialog.Builder	alertBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login1);

		init();
	}

	private void init() {
		etEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
		etPassword = (EditText) findViewById(R.id.etPassword);

		mDatabaseHandler	=	DatabaseHandler.getInstance(Login1.this);
		alertBuilder	=	new AlertDialog.Builder(Login1.this);
	}

	/**
	 * On click of login button.
	 * @param view passing view.
	 * 
	 * 
	 * 
	 */
	
	public void onLogin(View view) {
		if (!hasPopulated())
			return;

		
		
		String userEmail	=	etEmailAddress.getText().toString();
		Publicvar.email=userEmail;
		
		String userPassword	=	etPassword.getText().toString();
		if(userEmail.equalsIgnoreCase("admine@gmail.com")&&userPassword.equalsIgnoreCase("11111")){
			
				startActivity(new Intent(Login1.this,
						Admine.class));
				finish();
				
		
		}
		
		
		
		else{
		
		
	   	if (mDatabaseHandler.isEmailExist(userEmail)) {
			if (mDatabaseHandler.isPasswordMatch(userPassword)) {
				startActivity(new Intent(Login1.this,
						Firstpage.class));
				finish();
			} else {
				alertBuilder.setTitle("Attention")
						.setMessage("Sorry, entered password doesn't match our system!")
						.setCancelable(false)
						.setNegativeButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								});
				AlertDialog alert = alertBuilder.create();
				alert.show();
				
				
				startActivity(new Intent(Login1.this,
						Login1.class));
				finish();
			
			
			}
			
			
			
			//finish();
		
	   	
	   	
	   	} else {
			alertBuilder.setTitle("Attention")
					.setMessage("This email id is not exist! Please register.")
					.setCancelable(false)
					.setNegativeButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
									finish();
								}
							});
			AlertDialog alert = alertBuilder.create();
			alert.show();
			
			startActivity(new Intent(Login1.this,
					Login1.class));
			      finish();
			
		}
		
	
}	
	
	
		//finish();
	
	
	}
	/**
	 * This method will check user has entered
	 * value in edit text field or not.
	 * @return true if entered or false.
	 */
	private boolean hasPopulated() {

		if (etEmailAddress.getText().length() == 0) {
		etEmailAddress.setError("Required");
			return false;
		} else if (etPassword.getText().length() == 0) {
			etPassword.setError("Required");
			return false;
		}

		return true;
	}
	
	
	/*
	
	public void onLogin1(View view) {
		if (!hasPopulated())
			return;

		String Email	="admin@gmail.com";
		String Password	="111";
		
		if(Email.equalsIgnoreCase("admin@gmail.com")&&Password.equalsIgnoreCase("111")){

		if (mDatabaseHandler.isEmailExist(Email)) {
			if (mDatabaseHandler.isPasswordMatch(Password)) {
				startActivity(new Intent(Login1.this,
						Admine.class));
				finish();
			} else {
				alertBuilder.setTitle("Attention")
						.setMessage("Sorry, entered password doesn't match our system!")
						.setCancelable(false)
						.setNegativeButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								});
				AlertDialog alert = alertBuilder.create();
				alert.show();
			}
		} else {
			alertBuilder.setTitle("Attention")
					.setMessage("This email id is not exist! Please register.")
					.setCancelable(false)
					.setNegativeButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel();
									finish();
								}
							});
			AlertDialog alert = alertBuilder.create();
			alert.show();
		}
	}
		
	}

	*//**
	 * This method will check user has entered
	 * value in edit text field or not.
	 * @return true if entered or false.
	 *//*
	private boolean hasPopulated1() {

		if (etEmailAddress.getText().length() == 0) {
			etEmailAddress.setError("Required");
			return false;
		} else if (etPassword.getText().length() == 0) {
			etPassword.setError("Required");
			return false;
		}

		return true;
	}

	
	
	
*/	
	
	
	/**
	 * On click of cancel button
	 * closing the current activity.
	 * @param view passing view.
	 */
	public void onCancel(View view) {
		finish();
	}
}
