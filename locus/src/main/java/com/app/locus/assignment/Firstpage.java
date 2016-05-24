package com.app.locus.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

public class Firstpage extends Activity {

	private TextView textview;
	private static long back_pressed;
	private static final int TIME_DELAY = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		  Intent intent = getIntent();
		SharedPreferences preferences = getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
	      String username = intent.getStringExtra(SplashActivity.USER_NAME);
		 // String username1=intent.getStringExtra(Register.USER_NAME);

		textview=(TextView) findViewById(R.id.textView1);
		
		textview.setText(username);
		//textview.setText(username1);
		
		}

	public void onAssignment(View view){
		startActivity(new Intent(Firstpage.this,
				MainActivity.class));

	}
	
	public void onLogout(View view){

		//Creating an alert dialog to confirm logout
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage(" Are you sure you want to logout?");
		alertDialogBuilder.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						Intent logoutIntent = new Intent(Firstpage.this, SplashActivity.class);
						startActivity(logoutIntent);

						SharedPreferences sharedPreferences = getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
						Editor editor = sharedPreferences.edit();
						editor.putString("UniqueId", "");
						editor.commit();
						finish();
					}
				});

		alertDialogBuilder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}
				});

		//Showing the alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();


	}

	/*
	public void onBackPressed(){
		finish();
		Intent intent=new Intent(Firstpage.this,SplashActivity.class);
		startActivity(intent);
	}

*/    /**
     * Starts the chat with global config that was provided at init state via {@link com.zopim.android.sdk.api.ZopimChatApi#init(String)}
     *
     * @see com.zopim.sample.SampleApplication
     */
    public void buttonNoConfig(View view) {
        startActivity(new Intent(this, ZopimChatActivity.class));

        // Sample breadcrumb
        ZopimChat.trackEvent("Started chat without config");
    }


	public void onBackPressed() {

		if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
			super.onBackPressed();
		}
		else {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Exit Application?");
			alertDialogBuilder
					.setMessage("Click yes to exit!")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									moveTaskToBack(true);
									android.os.Process.killProcess(android.os.Process.myPid());
									System.exit(1);
								}
							})

					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							dialog.cancel();

							home();
						}
					});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

		}
		back_pressed = System.currentTimeMillis();

	}
	private void home() {
		startActivity(new Intent(Firstpage.this,Firstpage.class));

	}




}
