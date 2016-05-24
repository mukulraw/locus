package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Volunteertype extends Activity {

	private Button b1,b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volunteertype);
		
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.volunteertype, menu);
		return true;
	}
	
	
	
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		
		b1.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Volunteertype.this,Applicationform.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
             }  
               
         });  
 
		
		b2.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Volunteertype.this,Applicationform.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
            }  
              
        });  


}
}