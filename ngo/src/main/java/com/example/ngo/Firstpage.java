package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Firstpage extends Activity {
	
	private Button b1,b2,b4,b5,b6;
	//b3
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.firstpage, menu);
		return true;
	}
	
	
	
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		//b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button6);
		
		b1.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Post_Information.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
             }  
               
         });  
 
		
		b2.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Needy.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
            }  
              
        });  
		
/*	
		b3.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Donation1.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
            }  
              
        });  

		*/
		
		b4.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Helpline.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
           }  
             
       });  


		b5.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Profile.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
          }  
            
      });  

	

		b6.setOnClickListener(new OnClickListener(){  
			 
			 public void onClick(View view) { 
				 
				 Intent myIntent= new Intent(Firstpage.this,Index.class);
			       startActivity(myIntent);
				
				 //String value1="mdg";
				 //Toast.makeText(getApplicationContext(),String.valueOf(value1),Toast.LENGTH_LONG).show();  
          }  
            
      });  


}
}