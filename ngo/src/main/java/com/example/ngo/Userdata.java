package com.example.ngo;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Userdata extends Activity {

	private DatabaseHandler	mDatabaseHandler;
	private TextView textView; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdata);
		textView = (TextView) findViewById(R.id.textView1);
		
		
		try {
			mDatabaseHandler	=	DatabaseHandler.getInstance(Userdata.this);
			List<UserModel> userModels = mDatabaseHandler.getAllUserProfile();
			//Toast.makeText(getApplicationContext(), "mdatabase handler"+mDatabaseHandler, 3).show();
			String myData="";
			for (UserModel allUserModel : userModels) {
				//myData=myData+allUserModel.getFirstName()+" "+allUserModel.getLastName()+" "+allUserModel.getFirstName()+" "+allUserModel.getEmailAddress()+" "+allUserModel.getAddress()+" "+allUserModel.getMobileNumber()+" "+allUserModel.getSubject()+" "+allUserModel.getGender();
				myData=myData+allUserModel.getFirstName()+" "+allUserModel.getLastName()+"  "+allUserModel.getEmailAddress();
//				textView.setText(allUserModel.getFirstName());
//				textView.setText(allUserModel.getLastName());
//				textView.setText(allUserModel.getEmailAddress());
//				textView.setText(allUserModel.getAddress());
//				textView.setText(allUserModel.getSubject());
			  //Toast.makeText(getApplicationContext(), "user data is"+myData, 3).show();
			}
			
			textView.setText(myData);
			
		} catch(Exception ex){
			//Toast.makeText(getApplicationContext(), "exception is"+ex, 3).show();			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.userdata, menu);
		return true;
	}

}
