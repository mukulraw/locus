package com.example.ngo;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Postinformationdata extends Activity {

	private DatabaseHandler	mDatabaseHandler;
	private TextView textView; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_postinformationdata);
		textView = (TextView) findViewById(R.id.textView1);
		
		
		      mDatabaseHandler	=	DatabaseHandler.getInstance(Postinformationdata.this);
		      
		
		try {
			
			List<PostModle> postModle=null;
				
				
		
			postModle = mDatabaseHandler.getAllPostInformationdata();
					
			String myData="";
			for (PostModle allpostModle : postModle) {
				//myData=myData+allpostModle.getName()+" "+allpostModle.getMobileno()+" "+allpostModle.getLocation()+" "+allpostModle.getHelptype()+" "+allpostModle.getType();
				myData=myData+allpostModle.getName()+" "+allpostModle.getLocation()+" "+allpostModle.getHelptype()+" "+allpostModle.getType();
//				textView.setText(allUserModel.getFirstName());
//				textView.setText(allUserModel.getLastName());
//				textView.setText(allUserModel.getEmailAddress());
//				textView.setText(allUserModel.getAddress());
//				textView.setText(allUserModel.getSubject());	
				//Toast.makeText(getApplicationContext(), "Post information Data is"+myData, 3).show();
				
			}
	     	textView.setText(myData);
			
			
			
		} catch(Exception ex){
			//Toast.makeText(getApplicationContext(), "exception new here is"+ex, 3).show();			
		}
	}

	
		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.postinformationdata, menu);
		return true;
	}

}
