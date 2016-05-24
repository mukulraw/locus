package com.example.ngo;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity {

	
	private DatabaseHandler	mDatabaseHandler;
	private TextView textViewuser,textViewemail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		textViewuser = (TextView) findViewById(R.id.textView1);
		textViewemail = (TextView) findViewById(R.id.textView4);
		try {
			Log.d("Profile", "activity created..");
			
			
			mDatabaseHandler	=	DatabaseHandler.getInstance(Profile.this);
			Log.d("Profile", "db instance created..");
			UserModel userModel = mDatabaseHandler.getUserProfile();
			Log.d("Profile", "profile fetched.. ");
			String ussername=userModel.getFirstName()+"  "+userModel.getLastName();
			String usseemail=userModel.getEmailAddress();
			//Toast.makeText(getApplicationContext(), "username is"+userModel.getFirstName(), 3).show();
			
			//List<UserModel> userModels = mDatabaseHandler.getAllUserProfile();
			/*List<UserModel> userModels = mDatabaseHandler.getAllUserProfile();
			String data="";
			for (UserModel allUserModel : userModels) {
				data=data+allUserModel.getFirstName();
				Log.d("profile", "all data profile");
			}
			*/
			textViewuser.setText(ussername);
			textViewemail.setText(usseemail);
		} catch(Exception ex){
			Toast.makeText(getApplicationContext(), "exception is"+ex, 3).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
