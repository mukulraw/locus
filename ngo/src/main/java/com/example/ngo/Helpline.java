package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Helpline extends Activity {
	
	private Button b22,b33;
	// b11,
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helpline);
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.helpline, menu);
		return true;
	}
	
	
	
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		//b11=(Button)findViewById(R.id.button11);
		b22=(Button)findViewById(R.id.button22);
		b33=(Button)findViewById(R.id.button33);


	/*	
		b11.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Helpline.this,Emergencyhelp.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
             }  
               
         });  
 
*/		
		b22.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Helpline.this,Sendmassage.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
            }  
              
        });  
		
		b33.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Helpline.this,Email.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
            }  
              
        });  

		

	

}
}