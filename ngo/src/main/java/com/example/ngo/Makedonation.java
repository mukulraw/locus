package com.example.ngo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Makedonation extends Activity {

	private DatabaseHandler	mDatabaseHandler;
	private TextView textView; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_makedonation);
		textView = (TextView) findViewById(R.id.textView1);
		
		
		
		try{
		
		 mDatabaseHandler	=	DatabaseHandler.getInstance(Makedonation.this);
		 
	//	 List<UserModel> userModels = mDatabaseHandler.getAllUserProfile();
		 
		List <DonationModle> userdonation= mDatabaseHandler.getAllmakedonation();
		 
		 //Toast.makeText(getApplicationContext(), "mdatabase handler is "+mDatabaseHandler, 3).show();
		 
		     String myData="";
			for (DonationModle allUserdonation : userdonation) {
				//Toast.makeText(getApplicationContext(), "username is"+allUserdonation.getName(), 3).show();
				
				//myData=myData+allUserModel.getFirstName()+" "+allUserModel.getLastName()+" "+allUserModel.getFirstName()+" "+allUserModel.getEmailAddress()+" "+allUserModel.getAddress()+" "+allUserModel.getMobileNumber()+" "+allUserModel.getSubject()+" "+allUserModel.getGender();
				myData=myData+allUserdonation.getName()+"  "+allUserdonation.getMobile()+ " "+allUserdonation.getAmount();
//				textView.setText(allUserModel.getFirstName());
//				textView.setText(allUserModel.getLastName());
//				textView.setText(allUserModel.getEmailAddress());
//				textView.setText(allUserModel.getAddress());
//				textView.setText(allUserModel.getSubject());
			//	Toast.makeText(getApplicationContext(), "user data is"+myData, 3).show();
				
				
			}
			
			textView.setText(myData);
		
		
		
		}
		
		
	catch(Exception ex)
	{
		//Toast.makeText(getApplicationContext(), "exception is"+ex, 3).show();
		
	}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.makedonation, menu);
		return true;
	}

}
