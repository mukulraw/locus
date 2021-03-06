package com.app.locus.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.PreChatForm;
import com.zopim.android.sdk.prechat.ZopimChatActivity;


@SuppressWarnings("ALL")
public class Firstpage extends Activity {




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_flipper);
		  Intent intent = getIntent();

	      String username = intent.getStringExtra(SplashActivity.USER_NAME);

        ZopimChat.init(AccountKey.ACCOUNT_KEY).build();
		TextView textview=(TextView) findViewById(R.id.textView1);
		
		textview.setText(username);

		
		}

	public void onAssignment(View view){
		startActivity(new Intent(Firstpage.this,
				Assignment.class));

	}
	
	public void onLogout(View view){

		//Creating an alert dialog to confirm logout
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage(" Are you sure you want to logout?");
		alertDialogBuilder.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						Intent logoutIntent = new Intent(Firstpage.this, SplashActivity.class);
						startActivity(logoutIntent);

						//SharedPreferences sharedPreferences = getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
						//Editor editor = sharedPreferences.edit();
						//editor.putString("UniqueId", "");
						//editor.apply();
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


    public void buttonNoConfig(View view) {
      //  startActivity(new Intent(this, ZopimChatActivity.class));

        // Sample breadcrumb
       // ZopimChat.trackEvent("Started chat without config");

        PreChatForm preChatConfig = new PreChatForm.Builder()
                .name(PreChatForm.Field.OPTIONAL_EDITABLE)
                .email(PreChatForm.Field.OPTIONAL_EDITABLE)
                .phoneNumber(PreChatForm.Field.OPTIONAL_EDITABLE)
                .department(PreChatForm.Field.OPTIONAL_EDITABLE)
                .message(PreChatForm.Field.OPTIONAL_EDITABLE)
                .build();

        // build chat config
        ZopimChat.SessionConfig config = new ZopimChat.SessionConfig().preChatForm(preChatConfig);

        // start chat activity with config
        ZopimChatActivity.startActivity(this, config);

        // Sample breadcrumb
        ZopimChat.trackEvent("Started chat with optional pre-chat form");


    }


	public void onBackPressed() {



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

						}
					});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

	}
	private void home() {
		startActivity(new Intent(Firstpage.this,Firstpage.class));

	}




}
